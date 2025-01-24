package com.example.enums;

public enum AccountStatus {

	/*
	 * The account is in good standing. The account holder can perform transactions
	 * (deposits, withdrawals, fund transfers, etc.).
	 */
	ACTIVE,
	/*
	 * The account has had no transactions for a specific period (e.g., 12 months).
	 * The account holder may need to reactivate the account by performing a transaction or contacting the bank.
	 */
	INACTIVE,
	/*
	 * The account has been inactive for an extended period (e.g., 24 months or	more). Often requires additional verification to reactivate.
	 */
	DORMANT,
	/*
	 * The account has been permanently closed by the account holder or the bank. No
	 * transactions can be performed, and the account cannot be reopened.
	 */
	CLOSE
}
