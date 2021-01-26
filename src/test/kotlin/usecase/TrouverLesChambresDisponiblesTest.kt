package usecase

import domain.Chambre
import domain.RépertoireDeChambres
import domain.Réservation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class TrouverLesChambresDisponiblesTest {
    @Test
    fun `doit retourner la chambre disponible`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(Chambre(numéro = "101", capacité = 4))
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(
            répertoireDeChambres
        )

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter(
            nombreDePersonnes = 3,
            dateDArrivée = LocalDate.parse("2020-10-23"),
            dateDeDépart = LocalDate.parse("2020-10-24")
        )

        // Then
        val chambresAttendues = listOf(Chambre(numéro = "101", capacité = 4))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }

    @Test
    fun `doit retourner les chambres disponibles`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(
                    Chambre(numéro = "101", capacité = 4), Chambre(
                        numéro = "103",
                        capacité = 4
                    )
                )
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(
            répertoireDeChambres
        )

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter(
            nombreDePersonnes = 3,
            dateDArrivée = LocalDate.parse("2020-10-23"),
            dateDeDépart = LocalDate.parse("2020-10-24")
        )

        // Then
        val chambresAttendues = listOf(
            Chambre(numéro = "101", capacité = 4), Chambre(
                numéro = "103",
                capacité = 4
            )
        )
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }

    @Test
    fun `doit retourner les chambres disponibles pouvant accueillir le nombre de personnes`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(
                    Chambre(numéro = "101", capacité = 2),
                    Chambre(numéro = "103", capacité = 4)
                )
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(
            répertoireDeChambres
        )

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter(
            nombreDePersonnes = 3,
            dateDArrivée = LocalDate.parse("2020-10-23"),
            dateDeDépart = LocalDate.parse("2020-10-24")
        )

        // Then
        val chambresAttendues = listOf(Chambre(numéro = "103", capacité = 4))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }

    @Disabled
    @Test
    fun `doit retourner les chambres disponibles aux dates demandées`() {
        // Given
        val DATE_D_ARRIVÉE = LocalDate.parse("2020-10-23")
        val DATE_DE_DÉPART = DATE_D_ARRIVÉE.plusDays(2)
        val DATE_AVANT_L_ARRIVÉE = DATE_D_ARRIVÉE.minusDays(1)
        val DATE_APRÈS_L_ARRIVÉE = DATE_D_ARRIVÉE.plusDays(1)
        val DATE_AVANT_LE_DÉPART = DATE_DE_DÉPART.minusDays(1)
        val DATE_APRÈS_LE_DÉPART = DATE_DE_DÉPART.plusDays(1)
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(
                    Chambre(
                        numéro = "100",
                        capacité = 2,
                        réservations = listOf(Réservation(
                            numéroDeChambre = "101",
                            dateDeDébut = DATE_APRÈS_L_ARRIVÉE,
                            dateDeFin = DATE_AVANT_LE_DÉPART,
                        ))
                    ),
                    Chambre(
                        numéro = "101",
                        capacité = 2,
                        réservations = listOf(Réservation(
                            numéroDeChambre = "101",
                            dateDeDébut = DATE_AVANT_L_ARRIVÉE,
                            dateDeFin = DATE_APRÈS_L_ARRIVÉE,
                        ))
                    ),
                    Chambre(
                        numéro = "102",
                        capacité = 2,
                        réservations = listOf(Réservation(
                            numéroDeChambre = "102",
                            dateDeDébut = DATE_APRÈS_LE_DÉPART,
                            dateDeFin = DATE_APRÈS_LE_DÉPART.plusDays(2),
                        ))
                    ),
                    Chambre(numéro = "103", capacité = 2),
                )
            }
        }
        val trouverLesChambresDisponibles =
            TrouverLesChambresDisponibles(répertoireDeChambres)

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter(
            dateDArrivée = DATE_D_ARRIVÉE,
            dateDeDépart = DATE_DE_DÉPART,
            nombreDePersonnes = 2
        )

        // Then
        val chambresAttendues = listOf(Chambre(numéro = "102", capacité = 2),Chambre(numéro = "103", capacité = 2))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }
}