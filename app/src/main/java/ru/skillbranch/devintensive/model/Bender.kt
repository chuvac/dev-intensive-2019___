package ru.skillbranch.devintensive.model

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        when(question) {
            Question.NAME -> if(answer[0].isLowerCase())
                return "Имя должно начинаться с заглавной буквы\n${question.question}" to status.color
            Question.PROFESSION -> if(answer[0].isUpperCase())
                return "Профессия должна начинаться со строчной буквы\n${question.question}" to status.color
            Question.MATERIAL -> if(answer.contains(Regex("[0-9]")))
                return "Материал не должен содержать цифр\n${question.question}" to status.color
            Question.BDAY -> if(!answer.contains(Regex("\\d{${answer.length}}")))
                return "Год моего рождения должен содержать только цифры\n${question.question}" to status.color
            Question.SERIAL -> if(answer.length != 7 && !answer.contains(Regex("\\d{7}")))
                return "Серийный номер содержит только цифры, и их 7\n${question.question}" to status.color
            Question.IDLE -> return "На этом все, вопросов больше нет" to status.color
        }
        return if (question.answer.contains(answer)){
            question = question.nextQuestion()
            "Отлично - это правильный ответ!\n${if (question == Question.IDLE) 
                "На этом все, вопросов больше нет" else question.question}" to status.color
        } else{
            status = status.nextStatus()
            "Это не правильный ответ!\n${question.question}" to status.color
        }
    }

    enum class Status(val color : Triple<Int, Int, Int>){
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>){
        NAME("Как меня зовут?", listOf("Бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }

}