package com.kovalevich.banksystembgpb.models.passport

import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.models.country.Country
import com.kovalevich.banksystembgpb.models.registration.Registration
import com.kovalevich.banksystembgpb.models.worker.Worker
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.lang.NonNull

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = 'passports')
class Passport {

    @Id
    @NotNull
    @Column(unique = true)
    @Size(max = 14)
    String id

    @NotNull
    String lastName

    @NotNull
    String firstName

    @NotNull
    String middleName

    @NotNull
    String series

    @NotNull
    @Column(unique = true)
    int number

    @NotNull
    Date dateOfIssue

    @NotNull
    Date dateOfExpire

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'country_iso3code')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Country citizenship

    @NotNull
    String passportAuthority

    @NotNull
    boolean isMale

    @NotNull
    Date birthDate

    @NonNull
    boolean isMarried

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "passport")
    private Worker worker

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "passport")
    private Client client

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registration_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Registration registration

    @Override
    String toString() {
        return "Passport{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpire=" + dateOfExpire +
                ", citizenship=" + citizenship +
                ", passportAuthority='" + passportAuthority + '\'' +
                ", isMale=" + isMale +
                ", birthDate=" + birthDate +
                ", isMarried=" + isMarried +
                ", registration=" + registration +
                '}'
    }
}
