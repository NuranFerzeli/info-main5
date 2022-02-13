package com.firstnews.info.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "assemble")
public class Tedbir {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "assembleName",length = 400)
    String assembleName;
    @Column(name = "text",length=4000)
    private String text;
    @Column(name = "imagePath",length=400)
    private String imagePath;
    @Column(name = "imageId",length=400)
    private Long imageId;
    @Column(name="st")
    private boolean status;
    @Column(name="status_id")
    private Long status_id;
    @Column(name = "cdate")
    LocalDate cdate;
    @Column(name = "udate")
    LocalDate udate;

}
