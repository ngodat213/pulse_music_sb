package com.app.pulse_music_sb.Models;
import com.app.pulse_music_sb.Const.Constants;
import com.app.pulse_music_sb.Enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User{
    // -----------------= INFO USER =----------------- //
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 80)
    @Size.List({
            @Size(min = 8, message = "Password too short"),
            @Size(max = 80, message = "Password too long")
    })
    private String password;

    @Column(unique = true, nullable = false, length = 50)
    @Email()
    private String email;

    @Column(unique = true, nullable = false, length = 100)
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private CloudStorage avatar;

    @Column(nullable = true, length = 1000)
    @Size(max = 1000)
    private String bio;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private boolean enabled = true;

    private int countFail;
    private Date lockExpired;
    private String tokenResetPassword;
    private Date tokenResetPasswordExpired;

    public String getAvatarUrl(){
        if(avatar != null){
            return avatar.getUrl();
        }
        return Constants.DEFAULT_AVATAR;
    }

    // -----------------= INFO MUSIC =----------------- //
    @OneToMany(mappedBy = "user")
    private List<Music> tracks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Playlist> playLists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Album> albums = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_type",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "music_type_id")
    )
    private List<MusicType> userTypes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_liked_music",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private List<Music> userLiked = new ArrayList<>();

    public int sizeMySong(){
        return tracks.size();
    }
}
