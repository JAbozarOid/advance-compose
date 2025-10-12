package com.example.advancecompose.feature.interview.solid.principles

/**
 * 1-
 * there is a base abstract class BankAccount
 * in this base abstract class manage the balance amount and returned the getBalance
 * this base class has two method deposit and withdraw
 */

abstract class BankAccount(var initializeBalance: Double) {

    private var balance: Double = 0.0

    init {
        this.balance = initializeBalance
    }

    private fun getBalance(): Double = balance

    protected abstract fun deposit(amount: Double)
    protected abstract fun withdraw(amount: Double)

}

/**
 * 2-
 * there are two subclasses that inherit from BankAccount class
 * A- Saving Account -> interest rate : نرخ بهره بانکی
 * B- Checking Account
 */

class SavingAccount(private var initBalance: Double, private val interestRate: Double) :
    BankAccount(initializeBalance = initBalance) {

    private var rate: Double = 0.0

    init {
        this.rate = interestRate
    }

    override fun deposit(amount: Double) {
        initializeBalance += amount
    }

    override fun withdraw(amount: Double) {
        if (initializeBalance >= amount) {
            initializeBalance -= amount
        } else {
            throw IllegalArgumentException("insufficient funds")
        }
    }

    // *** calculate interest rate -> this method is requirement of the SavingAccount bank and it is not in the base class BankAccount ***
    fun addInterest() {
        initializeBalance += initializeBalance * rate
    }
}

// overDraftLimit -> محدودیت سقف برداشت
class CheckingAccount(val initBalance: Double, val overDraftLimit: Double) :
    BankAccount(initializeBalance = initBalance) {

    private var overDraft: Double = 0.0

    init {
        this.overDraft = overDraftLimit
    }

    override fun deposit(amount: Double) {
       initializeBalance += amount
    }

    override fun withdraw(amount: Double) {
        if (initializeBalance + overDraft >= amount) {
            initializeBalance -= amount
        }else {
            throw IllegalArgumentException("Overdraft limit Exceed")
        }
    }

}

/**
 * 3- there is a method inside of the program
 * we try to add interest to any BankAccount:
 */
private fun addMonthlyInterest(bankAccount: BankAccount) {
    /**
     * This violates LSP because it relies on checking the type of the object, and BankAccount does not have an addInterest method.
     */
    if (bankAccount is SavingAccount) {

        // here the subclass is changing the behavior of the superclass
        bankAccount.addInterest()
    }
}

/**
 * 4- Adhering to LSP
 * To adhere to LSP, we can introduce an InterestBearingAccount interface for accounts that can bear interest.
 */

// An interest-bearing account is a type of bank account that pays the customer an interest rate in exchange for them depositing their money at the bank.
public interface InterestBearingAccount {
    fun addInterest()
}

/**
 * 5- there are some accounts that can interest rate not all of the accounts
 * we can introduce an InterestBearingAccount interface for accounts that can bear interest.
 */

class BearingAccount(val initBalance : Double,val interestRate : Double) : BankAccount(initializeBalance = initBalance), InterestBearingAccount {

    private var rate : Double = 0.0

    init {
        this.rate = interestRate
    }
    override fun deposit(amount: Double) {
        initializeBalance += amount
    }

    override fun withdraw(amount: Double) {
        if (initializeBalance >= amount) {
            initializeBalance -= amount
        }else {
            throw IllegalArgumentException("Insufficient Funds")
        }
    }

    override fun addInterest() {
       initializeBalance += initializeBalance * rate
    }
}

/**
 * 6- we can safely use the InterestBearingAccount interface without breaking LSP
 */
private fun addMonthlyInterest(interestBearingAccount: InterestBearingAccount) {
    interestBearingAccount.addInterest()
}

