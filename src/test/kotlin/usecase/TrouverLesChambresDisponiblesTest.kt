package usecase

import domain.Chambre
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import usecase.TrouverLesChambresDisponibles

internal class TrouverLesChambresDisponiblesTest {
    @Test
    fun `doit retourner la chambre disponible`() {
        // Given
        val trouverLesChambresDisponibles = TrouverLesChambresDisponibles()

        // When
        val chambresDisponibles = trouverLesChambresDisponibles.exécuter()

        // Then
        val chambresAttendues = listOf(Chambre("101"))
        assertThat(chambresDisponibles).isEqualTo(chambresAttendues)
    }
}