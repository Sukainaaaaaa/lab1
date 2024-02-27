public class PlatformTransporter {
    private boolean platform;
    public void raise(Transporter trans) {
        if (trans.currentSpeed != 0) {
            throw new IllegalArgumentException("Transporter must be stationary");
        }
            platform = true;

    }

    public void lower(Transporter trans) {
        if (trans.currentSpeed != 0) {
            throw new IllegalArgumentException("Transporter must be stationary");
        }
            platform = false;

    }

}
