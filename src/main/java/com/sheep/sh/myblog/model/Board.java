package com.sheep.sh.myblog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length = 100)
    private String title;

    @Lob
    private String content;

    @Setter
    private int count;

    @CreationTimestamp
    private Timestamp createdDate;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Reply> reply;

}
