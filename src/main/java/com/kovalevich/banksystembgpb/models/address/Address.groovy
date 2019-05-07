package com.kovalevich.banksystembgpb.models.address

import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.models.country.Country
import com.kovalevich.banksystembgpb.models.registration.Registration
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.lang.NonNull

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'addresses')
class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    long id

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'country_iso3code')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Country country

    @NonNull
    String city

    @NonNull
    String street

    @NonNull
    short buildingNumber

    @NonNull
    boolean isApartment

    short apartmentNumber

    @NonNull
    int postCode

    @OneToMany(mappedBy = 'address')
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Registration> registrationList

    @OneToMany(mappedBy = 'address')
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Client> clientList

    @Override
    String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", isApartment=" + isApartment +
                ", apartmentNumber=" + apartmentNumber +
                ", postCode=" + postCode +
                '}'
    }
}
