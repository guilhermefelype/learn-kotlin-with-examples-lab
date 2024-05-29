import java.util.Scanner

enum class Level { BASIC, INTERMEDIATE, ADVANCED }

data class User(val name: String, val email: String)

data class EducationalContent(val name: String, val duration: Int = 60, val level: Level)

class Training(val name: String, val contents: MutableList<EducationalContent> = mutableListOf(), val level: Level) {

    private val enrolledUsers = mutableListOf<User>()

    fun showCompleteTraining() {
        println("Training $name:")
        println("Level: $level")
        showContents()
        showStudents()
    }

    fun enroll(vararg users: User) {
        users.forEach { user ->
            if (enrolledUsers.contains(user)) {
                println("The user: ${user.name}, is already enrolled")
            } else {
                enrolledUsers.add(user)
                println("User ${user.name} successfully enrolled")
            }
        }
    }

    fun unenroll(vararg users: User) {
        users.forEach { user ->
            if (enrolledUsers.contains(user)) {
                enrolledUsers.remove(user)
                println("User ${user.name} successfully unenrolled")
            } else {
                println("The user: ${user.name}, is not enrolled")
            }
        }
    }

    fun showStudents() {
        println("Students enrolled in the training $name:")
        enrolledUsers.forEach { enrolledUser ->
            println("${enrolledUser.name} - ${enrolledUser.email}")
        }
        println()
    }

    fun addContents(vararg contents: EducationalContent) {
        contents.forEach { content ->
            if (this.contents.contains(content)) {
                println("The content ${content.name} has already been added")
            } else {
                this.contents.add(content)
                println("Content ${content.name} successfully added")
            }
        }
    }

    fun removeContents(vararg contents: EducationalContent) {
        contents.forEach { content ->
            if (this.contents.contains(content)) {
                this.contents.remove(content)
                println("Content ${content.name} successfully removed")
            } else {
                println("The content ${content.name} has not been added")
            }
        }
    }

    fun showContents() {
        println("Contents of the training $name:")
        this.contents.forEach { content ->
            println("${content.name} - ${content.duration} min - ${content.level}")
        }
        println()
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val trainings = mutableListOf<Training>()

    while (true) {
        println("Choose an option:")
        println("1. Show complete training")
        println("2. Enroll user")
        println("3. Unenroll user")
        println("4. Show students")
        println("5. Add content")
        println("6. Remove content")
        println("7. Show contents")
        println("8. Register new training")
        println("9. Remove training")
        println("10. List trainings")
        println("11. Exit")

        when (scanner.nextLine()) {
            "1" -> {
                println("Enter the training name:")
                val name = scanner.nextLine()
                val training = trainings.find { it.name == name }
                if (training != null) {
                    training.showCompleteTraining()
                } else {
                    println("Training not found")
                }
            }
            "2" -> {
                println("Enter the training name:")
                val trainingName = scanner.nextLine()
                val training = trainings.find { it.name == trainingName }
                if (training != null) {
                    println("Enter the user's name:")
                    val name = scanner.nextLine()
                    println("Enter the user's email:")
                    val email = scanner.nextLine()
                    training.enroll(User(name, email))
                } else {
                    println("Training not found")
                }
            }
            "3" -> {
                println("Enter the training name:")
                val trainingName = scanner.nextLine()
                val training = trainings.find { it.name == trainingName }
                if (training != null) {
                    println("Enter the name of the user to unenroll:")
                    val name = scanner.nextLine()
                    println("Enter the email of the user to unenroll:")
                    val email = scanner.nextLine()
                    training.unenroll(User(name, email))
                } else {
                    println("Training not found")
                }
            }
            "4" -> {
                println("Enter the training name:")
                val name = scanner.nextLine()
                val training = trainings.find { it.name == name }
                if (training != null) {
                    training.showStudents()
                } else {
                    println("Training not found")
                }
            }
            "5" -> {
                println("Enter the training name:")
                val trainingName = scanner.nextLine()
                val training = trainings.find { it.name == trainingName }
                if (training != null) {
                    println("Enter the name of the content:")
                    val name = scanner.nextLine()
                    println("Enter the duration of the content (minutes):")
                    val duration = scanner.nextLine().toIntOrNull() ?: 60
                    println("Choose the level of the content (BASIC, INTERMEDIATE, ADVANCED):")
                    val level = Level.valueOf(scanner.nextLine().uppercase())
                    training.addContents(EducationalContent(name, duration, level))
                } else {
                    println("Training not found")
                }
            }
            "6" -> {
                println("Enter the training name:")
                val trainingName = scanner.nextLine()
                val training = trainings.find { it.name == trainingName }
                if (training != null) {
                    println("Enter the name of the content to remove:")
                    val name = scanner.nextLine()
                    println("Enter the duration of the content (minutes):")
                    val duration = scanner.nextLine().toIntOrNull() ?: 60
                    println("Choose the level of the content (BASIC, INTERMEDIATE, ADVANCED):")
                    val level = Level.valueOf(scanner.nextLine().uppercase())
                    training.removeContents(EducationalContent(name, duration, level))
                } else {
                    println("Training not found")
                }
            }
            "7" -> {
                println("Enter the training name:")
                val name = scanner.nextLine()
                val training = trainings.find { it.name == name }
                if (training != null) {
                    training.showContents()
                } else {
                    println("Training not found")
                }
            }
            "8" -> {
                println("Enter the name of the new training:")
                val name = scanner.nextLine()
                println("Choose the level of the training (BASIC, INTERMEDIATE, ADVANCED):")
                val level = Level.valueOf(scanner.nextLine().uppercase())
                val newTraining = Training(name, mutableListOf(), level)
                trainings.add(newTraining)
                println("Training $name successfully registered")
            }
            "9" -> {
                println("Enter the name of the training to remove:")
                val name = scanner.nextLine()
                val training = trainings.find { it.name == name }
                if (training != null) {
                    trainings.remove(training)
                    println("Training $name successfully removed")
                } else {
                    println("Training not found")
                }
            }
            "10" -> {
                println("List of trainings:")
                trainings.forEach { training ->
                    println("Name: ${training.name}, Level: ${training.level}")
                }
                println()
            }
            "11" -> {
                println("Exiting the system...")
                return
            }
            else -> println("Invalid option. Try again.")
        }
    }
}
