package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class PériodeTest {

    @Test
    fun `deux périodes ne se chevauchent pas si la date de début de la première est avant la date de fin de la deuxième`() {
        // Given
        val période1 = Période(début = LocalDate.parse("2020-10-23"), fin = LocalDate.parse("2020-10-25"))
        val période2 = Période(début = LocalDate.parse("2020-10-22"), fin = LocalDate.parse("2020-10-24"))

        // When
        val estChevauché = période1.chevauche(période2)

        // Then
        assertThat(estChevauché).isEqualTo(true)
    }

    @Test
    fun `deux périodes se chevauchent si la date de début de la première est avant la date de fin de la deuxième`() {
        // Given
        val période1 = Période(début = LocalDate.parse("2020-10-23"), fin = LocalDate.parse("2020-10-25"))
        val période2 = Période(début = LocalDate.parse("2020-10-22"), fin = LocalDate.parse("2020-10-24"))

        // When
        val estChevauché = période1.chevauche(période2)

        // Then
        assertThat(estChevauché).isEqualTo(true)
    }
}