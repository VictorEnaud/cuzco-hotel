package domain

import java.time.LocalDate

class Période(début: LocalDate, fin: LocalDate) {
    fun chevauche(autrePériode: Période): Boolean {
        return true
    }
}