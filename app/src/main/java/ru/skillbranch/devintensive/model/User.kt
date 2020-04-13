package ru.skillbranch.devintensive.model

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id:String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    var lastVisit:Date? = null,
    var isOnline:Boolean = false
) {


    constructor(id:String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
        println("It's Alive!!! \n"+
                "${if(lastName === "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n"
        )
        println("His initials is ${Utils.toInitials(this.firstName, this.lastName)}")
        println("His translit name is ${Utils.transliteration(this.firstName + " " + this.lastName, "_")}")
    }

    companion object Factory {
        private var lastId : Int = -1
        fun makeUser(fullName:String?) : User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    class Builder() {
        lateinit var user : User

        fun id(id : String) : Builder {
            user = User(id)
            return this
        }
        fun firstName(firstName : String) : Builder {
            user.firstName = firstName
            return this
        }
        fun lastName(lastName : String) : Builder {
            user.lastName = lastName
            return this
        }
        fun avatar(avatar : String) : Builder {
            user.avatar = avatar
            return this
        }
        fun rating(rating : Int) : Builder {
            user.rating = rating
            return this
        }
        fun respect(respect : Int) : Builder {
            user.respect = respect
            return this
        }
        fun lastVisit(lastVisit : Date) : Builder {
            user.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline : Boolean) : Builder {
            user.isOnline = isOnline
            return this
        }

        fun  build(): User {
            return user
        }

    }

//    public fun Builder() : User {
//        return User()
//    }
//
//    public fun id(s : String) : User {
//        id = s
//        return this
//    }

}