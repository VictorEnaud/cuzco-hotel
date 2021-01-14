package domain

data class Chambre(val numéro: String, val capacité: Int) {

    fun peutAccueillir(nombreDePersonnes: Int): Boolean {
        return capacité >= nombreDePersonnes
    }

}
