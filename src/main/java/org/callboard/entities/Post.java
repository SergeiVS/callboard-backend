package org.callboard.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @NotBlank
    @Column(nullable = false)
    private String header;

    @NotBlank
    @Column(nullable = false)
    private String description;

    private String photoLink;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @NotNull
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id")
    @NotNull
    private User user;

    public Post(String header, String description, String photoLink, Subject subject, User user) {
        this.header = header;
        this.description = description;
        this.photoLink = photoLink;
        this.subject = subject;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();

        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();

        if (!thisEffectiveClass.equals(oEffectiveClass)) return false;

        Post post = (Post) o;
        return postId != null && Objects.equals(postId, post.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId != null ? postId : 0);
    }
}