package com.example.rolling_paper.entity;

import com.example.rolling_paper.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class CommentEntity {
    @Id
    @GeneratedValue (strategy = GenerationType. IDENTITY)
    private int commentNo;

    @Column(nullable = false, length = 15)
    private String writer;

    @Column(nullable = false, length = 200)
    private String context;

    @Column(name = "`isPublic`")
    @ColumnDefault("0")
    private Boolean isPublic;

    @Column(name = "isCheck")
    @ColumnDefault("0")
    private Boolean isCheck;

    @Column(name = "isKeep")
    @ColumnDefault("0")
    private Boolean isKeep;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate() {
        if (this.isCheck == null) {
            this.isCheck = false;
        }
        if (this.isKeep == null) {
            this.isKeep = false;
        }
        if (this.isPublic == null) {
            this.isPublic = false;
        }
    }

}
