/**
 * User entity (sama seperti model user)
 */

 package com.dira.multidb.database1.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;
}

// @Id: Menandakan bahwa atribut id adalah kunci utama (primary key) dari entitas ini.

// @Column(name = "first_name"):
// Memberitahu program bahwa properti "firstName" akan disimpan di kolom dengan nama "first_name" di dalam tabel basis data. Ini seperti memberi petunjuk tentang bagaimana nama kolom seharusnya.

// @ManyToOne:
// Mengindikasikan bahwa properti "user" adalah bagian dari hubungan di mana setiap objek "Contact" berkaitan dengan satu objek "User", tetapi satu objek "User" bisa berkaitan dengan banyak objek "Contact". Ini mirip dengan hubungan antara pemilik rumah dan mobil, di mana setiap rumah memiliki satu pemilik, tetapi satu pemilik bisa memiliki banyak rumah.

// @JoinColumn(name = "username", referencedColumnName = "username"):
// Menentukan bahwa kolom "username" di tabel "Contact" akan digunakan sebagai tempat untuk menyimpan informasi tentang koneksi dengan tabel "User". Artinya, nilai dalam kolom "username" di "Contact" akan mengacu pada nilai dalam kolom "username" di "User". Ini seperti menyambungkan dua bagian puzzle dengan kunci yang sesuai.

// properti "email" dan "phone", tidak adanya anotasi @Column karena default pemetaan properti ke kolom menggunakan nama yang sama. Jika perlu mengonfigurasi lebih lanjut, dapat menambahkan anotasi @Column dengan parameter sesuai ke properti tersebut.