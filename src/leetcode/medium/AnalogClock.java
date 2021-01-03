package leetcode.medium;

public class AnalogClock {

    public double angleClock(int hour, int minutes) {
        int degreePerSlot = 6;
        int degreePerHour = 5;
        double oneHourMinutes = 60;
        double hourDegree = (hour % 12) * degreePerHour * degreePerSlot +
                degreePerHour * degreePerSlot * minutes / oneHourMinutes;
        double minutesDegree = minutes * degreePerSlot;
        double diff = Math.abs(hourDegree - minutesDegree);
        return Math.min(diff, 360 - diff);
    }

}
