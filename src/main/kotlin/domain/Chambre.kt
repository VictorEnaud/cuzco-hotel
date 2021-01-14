package domain

data class Chambre(val numéro: String, val capacité: Int, val estLibre: Boolean) {

    fun peutAccueillir(nombreDePersonnes: Int): Boolean {
        return estLibre && capacité >= nombreDePersonnes
    }

}
