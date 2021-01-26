package usecase

import domain.Chambre
import domain.RépertoireDeChambres
import java.time.LocalDate

class TrouverLesChambresDisponibles(
    private val répertoireDeChambres: RépertoireDeChambres
) {
    fun exécuter(nombreDePersonnes: Int, dateDArrivée: LocalDate, dateDeDépart: LocalDate): List<Chambre> {
        return répertoireDeChambres.récupérerLesChambres()
            .filter { chambre -> chambre.peutAccueillir(nombreDePersonnes, dateDArrivée) }
    }
}