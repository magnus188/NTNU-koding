package oving2;

import java.util.Arrays;

/**
 * Vehicle
 */
public class Vehicle {

    private char type;
    private char fuelType;
    private String regNo;

    private String[] possibleTypes = { "M", "C" };
    private String[] possibleFuelTypes = { "D", "H", "E", "G" };
    private String[] elReg = { "EL", "EK" };
    private String hydReg = "HY";

    private int mcRegLength = 6;
    private int carRegLentgh = 7;

    public Vehicle(char type, char fuelType, String regNo) {

        if (Arrays.asList(possibleTypes).contains(String.valueOf(type))
                && Arrays.asList(possibleFuelTypes).contains(String.valueOf(fuelType))) {
            this.type = type;
            this.fuelType = fuelType;
            setRegistrationNumber(regNo);
        } else {
            throw new IllegalArgumentException("Invalid values");
        }
    }

    private boolean isValidPrefix(String regNo, char fuelType) {

        final String prefix = regNo.substring(0, 2);
        final String suffix = regNo.substring(2, regNo.length());

        char[] charArray = prefix.toCharArray();

        // check if only numbers in suffix and valid prefix characters
        if (!suffix.matches("[0-9]+") || !prefix.matches("[A-Z]{2}")) {
            return false;
        }

        // check if valid prefix for fuel type
        switch (fuelType) {
            // Electrical
            case 'E':
                if (Arrays.asList(elReg).contains(prefix)) {
                    return true;
                }
                break;
            // Hydrogen
            case 'H':
                if (Arrays.asList(hydReg).contains(prefix) && type == 'C') {
                    return true;
                }
                break;
            // Diesel
            default:
                if (!Arrays.asList(hydReg).contains(prefix) && !Arrays.asList(elReg).contains(prefix)) {
                    return true;
                }
                break;
        }
        return false;
    }

    // check if valid length
    private boolean isValidRegLength(String regNo) {
        if (type == 'M' && regNo.length() == mcRegLength) {
            return true;
        } else if (type == 'C' && regNo.length() == carRegLentgh) {
            return true;

        }
        return false;
    }

    // set reg no
    public void setRegistrationNumber(String regNo) {
        if (isValidPrefix(regNo, fuelType) && isValidRegLength(regNo)) {
            this.regNo = regNo;
        } else {
            throw new IllegalArgumentException("Invalid registration number");
        }
    }

    public char getFuelType() {
        return fuelType;
    }

    public String getRegistrationNumber() {
        return regNo;
    }

    public char getVehicleType() {
        return type;
    }

    public static void main(String[] args) {
        Vehicle myVehicle = new Vehicle('C', 'G', "EL12345");

        myVehicle.getRegistrationNumber();
    }

}