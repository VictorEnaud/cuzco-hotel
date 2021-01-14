package usecase

import domain.Chambre
import domain.RépertoireDeChambres

class TrouverLesChambresDisponibles(private val répertoireDeChambres: RépertoireDeChambres) {
    fun exécuter(nombreDePersonnes: Int): List<Chambre> {
        return répertoireDeChambres.récupérerLesChambres()
            .filter { chambre -> chambre.peutAccueillir(nombreDePersonnes) }
    }
}