package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.CloudStorage;
import com.app.pulse_music_sb.Models.Music;
import com.app.pulse_music_sb.Models.MusicType;
import com.app.pulse_music_sb.Models.User;
import com.app.pulse_music_sb.Repository.MusicRepository;
import com.app.pulse_music_sb.Request.DTO.PlaylistTrackDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateMusic;
import com.app.pulse_music_sb.Request.Request.RequestMusicTypes;
import com.app.pulse_music_sb.Request.Request.RequestUpdateMusic;
import com.app.pulse_music_sb.Service.Interface.IMusicService;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Util.MP3DurationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MusicService implements IMusicService {
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private PaginationService paginationService;
    @Autowired
    private CloudService cloudService;
    @Autowired
    private UserService userService;

    @Override
    public Page<Music> findAllBy(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return musicRepository.findAll(pageable);
    }

    @Override
    public List<Music> findAll() {
        return musicRepository.findAll();
    }

    @Override
    public Music findById(String id) {
        return musicRepository.findById(id).get();
    }

    @Override
    public Music save(RequestCreateMusic createMusic, MultipartFile image, MultipartFile mp3) {
        User user = userService.findById(createMusic.getUserId());

        CloudStorage imageMusic = cloudService.uploadFile(image, false);
        CloudStorage mp3Music = cloudService.uploadFile(mp3, true);

        int durationInSeconds = 0;
        try (InputStream mp3Stream = mp3.getInputStream()) {
            durationInSeconds = MP3DurationUtil.getDurationInSeconds(mp3Stream);
        } catch (Exception e) {
            throw new RuntimeException("Error get GetDurationInSeconds()");
        }

        Music music = new Music();
        music.setTitle(createMusic.getTitle());
        music.setDescription(createMusic.getDescription());
        music.setImage(imageMusic);
        music.setMp3(mp3Music);
        music.setUser(user);
        music.setDuration(durationInSeconds);
        music.setHeartCount(0);
        music.setPlayCount(0);
        return musicRepository.save(music);
    }

    @Override
    public Music update(String id, RequestUpdateMusic request, MultipartFile image, MultipartFile mp3){
        Music music = musicRepository.findById(id).get();
        music.setTitle(request.getTitle());
        music.setDescription(request.getDescription());

        if((image != null && !image.isEmpty()) ){
            CloudStorage imageMusic = cloudService.uploadFile(image, false);
            music.setImage(imageMusic);
        }

        if((mp3 != null && !mp3.isEmpty()) ){
            CloudStorage mp3Music = cloudService.uploadFile(mp3, true);

            int durationInSeconds = 0;
            try (InputStream mp3Stream = mp3.getInputStream()) {
                durationInSeconds = MP3DurationUtil.getDurationInSeconds(mp3Stream);
            } catch (Exception e) {
                throw new RuntimeException("Error get GetDurationInSeconds()");
            }

            music.setMp3(mp3Music);
            music.setDuration(durationInSeconds);
        }

        return musicRepository.save(music);
    }

    @Override
    public void deleteById(String id) {
        musicRepository.deleteById(id);
    }

    @Override
    public List<RequestMusicTypes> findAllMusicTypes(Page<MusicType> types) {
        List<RequestMusicTypes> res = new ArrayList<>();
        for (MusicType type: types) {
            res.add(new RequestMusicTypes(type, musicRepository.findByMusicType(type)));
        }
        return res;
    }

    @Override
    public List<Music> findByIds(List<String> ids) {
        return ids.stream()
                .map(id -> musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found")))
                .collect(Collectors.toList());
    }

    @Override
    public PlaylistTrackDTO getPlaylistTrack() {
        return musicRepository.findTopLikedMusic().get().toDTO();
    }

    @Override
    public PlaylistTrackDTO getRandomTrack() {
        List<Music> musics = findAll();
        Random rand = new Random();
        Music randomElement = musics.get(rand.nextInt(musics.size()));
        return randomElement.toDTO();
    }

    @Override
    public List<Music> searchMusic(String query) {
        if(query.equals("")){
            return musicRepository.findAll();
        }
        return musicRepository.searchMusics(query);
    }
}