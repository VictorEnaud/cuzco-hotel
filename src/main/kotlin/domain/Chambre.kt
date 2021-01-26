package domain

import java.time.LocalDate

data class Chambre(val numéro: String, val capacité: Int, val réservations: List<Réservation> = emptyList()) {

    fun peutAccueillir(nombreDePersonnes: Int, dateDArrivée:LocalDate): Boolean {
        val estRéservée = réservations
            .filter { it.dateDeDébut.isBefore(dateDArrivée) }
            .isEmpty()
        return capacité >= nombreDePersonnes && estRéservée
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Chambre

        if (numéro != other.numéro) return false

        return true
    }

    override fun hashCode(): Int {
        return numéro.hashCode()
    }
}
