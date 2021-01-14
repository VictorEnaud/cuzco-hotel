package usecase

import domain.Chambre
import domain.RépertoireDeChambres
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import usecase.TrouverLesChambresDisponibles

internal class TrouverLesChambresDisponiblesTest {
    @Test
    fun `doit retourner la chambre disponible`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(Chambre("101"))
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(répertoireDeChambres)

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter()

        // Then
        val chambresAttendues = listOf(Chambre("101"))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }

    @Test
    fun `doit retourner les chambres disponibles`() {
        // Given
        val répertoireDeChambres = object : RépertoireDeChambres {
            override fun récupérerLesChambres(): List<Chambre> {
                return listOf(Chambre("101"), Chambre("103"))
            }
        }
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles(répertoireDeChambres)

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter()

        // Then
        val chambresAttendues = listOf(Chambre("101"), Chambre("103"))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }
}