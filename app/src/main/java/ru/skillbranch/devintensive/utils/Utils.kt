package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName (fullName : String?) : Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var translitString = ""

        for (char: Char in payload) {
            translitString += when(char.toString()) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                " " -> divider
                else -> char
            }
        }
        return translitString
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName == null && lastName == null) return null
        val firstInitial = if (firstName != null && firstName != "" && firstName != " ") firstName.get(0).toUpperCase().toString() else ""
        val lastInitial = if (lastName != null && lastName != "" && lastName != " ") lastName.get(0).toUpperCase().toString() else ""
        return firstInitial + lastInitial
    }
}