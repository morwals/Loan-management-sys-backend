package com.example.prac.rough.Controller

import com.example.prac.rough.model.LoanApplication
import com.example.prac.rough.model.Logindata
import com.example.prac.rough.model.User
import com.example.prac.rough.repository.LoanRepo
import com.example.prac.rough.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("*"), allowedHeaders = arrayOf("*"))
class Controller(@Autowired val userRepo: UserRepo, @Autowired val userdetailsRepo: LoanRepo) {

    @GetMapping("/getuser/{id}")
    fun getUser(@PathVariable id : Int): User? {
        userRepo.findAll().map {
            if (it.user_id == id) {
                println(it.user_id)
                return it
            }
        }
        return null
    }

    @GetMapping("/loandetails/{id}")
    fun getdetails(@PathVariable id : Int): LoanApplication? {
        userdetailsRepo.findAll().map {
            if (it.customer_id == id) {
                println(it.approved)
                return it
            }
        }
        return null
    }

    @GetMapping("/getallusers")
    fun getAllUsers(): MutableList<User> {
        var result=mutableListOf<User>()
        userRepo.findAll().map {
            if(it.isAdmin!=1){
                result.add(it)
            }
        }
        return result
    }

    @GetMapping("/getalladmins")
    fun getAllAdmins(): MutableList<User> {
        var result= mutableListOf<User>()
        userRepo.findAll().map {
            if(it.isAdmin==1){
                result.add(it)
            }
        }
        return result
    }

    var userExists=false
    @GetMapping("/loginCheck")
    fun loginCheck(): Boolean {
        return userExists
    }

    @PostMapping("/loginCheck")
    fun loginCheck(@RequestBody loginData:Logindata): MutableList<Int> {

        var result=mutableListOf<User>()
        userRepo.findAll().map {
            if(it.user_name==loginData.user_name && it.user_password==loginData.user_password){
                println("logged in")
                var u1= mutableListOf<Int>(1,it.user_id,it.isAdmin)
                return u1
            }
        }
        return mutableListOf(0)
    }



    var id :Int = 1

    @PostMapping("/addUser")
//    @CrossOrigin(origins = arrayOf("https://localhost:3000"))
    fun add_user(@RequestBody user: User): User {
        user.user_id = id++
        return userRepo.save(user)
    }

    @PostMapping("/addUserDetails")
    fun add_userDetails(@RequestBody userdetails: LoanApplication): LoanApplication {
        return userdetailsRepo.save(userdetails)
    }

    @GetMapping("/loanapplications")
    fun getAllUserDetails(): MutableList<LoanApplication> {
        var result=mutableListOf<LoanApplication>()
        userdetailsRepo.findAll().map {
            if(it.admin!=1){
                result.add(it)
            }
        }
        println(result[0].approved)
        return result
    }

    @PutMapping("/approveLoanApplication")
    fun approveLoanApplicatino(@RequestBody id:Int): LoanApplication? {
        userdetailsRepo.findAll().map {
            if(it.customer_id == id){
                it.approved = 1;
                return userdetailsRepo.save(it);
            }
        }
        return null;
    }


}