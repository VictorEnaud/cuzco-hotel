package usecase

import domain.Chambre
import domain.RépertoireDeChambres

class TrouverLesChambresDisponibles(private val répertoireDeChambres: RépertoireDeChambres) {
    fun exécuter(): List<Chambre> {
        return répertoireDeChambres.récupérerLesChambres()
    }
}