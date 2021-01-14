package usecase

import domain.Chambre
import domain.RépertoireDeChambres
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TrouverLesChambresDisponiblesTest {
    @Test
    fun `doit retourner la chambre disponible`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(Chambre(numéro = "101", capacité = 4, estLibre = true))
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(répertoireDeChambres)

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter(nombreDePersonnes = 3)

        // Then
        val chambresAttendues = listOf(Chambre(numéro = "101", capacité = 4, estLibre = true))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }

    @Test
    fun `doit retourner les chambres disponibles`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(Chambre(numéro = "101", capacité = 4, estLibre = true), Chambre(
                    numéro = "103",
                    capacité = 4,
                    estLibre = true
                ))
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(répertoireDeChambres)

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter(nombreDePersonnes = 3)

        // Then
        val chambresAttendues = listOf(Chambre(numéro = "101", capacité = 4, estLibre = true), Chambre(
            numéro = "103",
            capacité = 4,
            estLibre = true
        ))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }

    @Test
    fun `doit retourner les chambres disponibles pouvant accueillir le nombre de personnes`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(Chambre(numéro = "101", capacité = 2, estLibre = true), Chambre(
                    numéro = "103",
                    capacité = 4,
                    estLibre = true
                ))
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(répertoireDeChambres)

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter(nombreDePersonnes = 3)

        // Then
        val chambresAttendues = listOf(Chambre(numéro = "103", capacité = 4, estLibre = true))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }

    @Test
    fun `doit retourner les chambres disponibles aux dates demandées`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(
                    Chambre(numéro = "101", capacité = 2, estLibre=false),
                    Chambre(numéro = "103", capacité = 4, estLibre=true)
                )
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(répertoireDeChambres)

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter(
            nombreDePersonnes = 2
        )

        // Then
        val chambresAttendues = listOf(Chambre(numéro = "103", capacité = 4, estLibre = true))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }
}