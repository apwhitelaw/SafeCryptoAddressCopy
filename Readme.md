![Address changed](Demo.PNG?raw=true)

# SafeCryptoAddressCopy

The intent of this application is to keep track of your clipboard and if you copy a Bitcoin address, 
the application will alert you if the address is altered or changed. This is due to the fact that
there is malware that will keep track of your system's clipboard until it sees a Bitcoin address, and
then will immediately replace it with a different address. If you are not paying attention you will
paste the address and send your money to someone else, and you will have suddenly lost all your funds.
Newer users to Bitcoin are especially vulnerable because a Bitcoin address looks fairly complex and
therefore they are less likely to check that the address matches once they have pasted it.

The ExampleMalware class is a simple representation of a software detecting that your clipboard contains 
a Bitcoin address, and changing it to a different address. This gives the ability to test that the main
AddressTracker class actually functions properly. By running AddressTracker and copying a Bitcoin address,
we can then run ExampleMalware and see that it immediately detects a Bitcoin address and replaces it.

ExampleMalware will print "ADDRESS HIJACKED" to indicate it was successful. AddressTracker will display an 
alert saying "BITCOIN ADDRESS WAS ALTERED" in order to alert the user that the address was changed. It also
prints "ADDRESS CHANGED", but the alert is much more useful for making sure the user actually sees it.