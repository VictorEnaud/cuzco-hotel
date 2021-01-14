package usecase

import domain.Chambre

class TrouverLesChambresDisponibles {
    fun exécuter(): List<Chambre> {
        return listOf(Chambre("101"))
    }
}