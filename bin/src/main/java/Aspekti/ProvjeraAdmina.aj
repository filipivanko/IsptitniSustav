package Aspekti;
aspect ProvjeraAdmina{
    before(): move() {
        logStream.println("about to move");
    }
}