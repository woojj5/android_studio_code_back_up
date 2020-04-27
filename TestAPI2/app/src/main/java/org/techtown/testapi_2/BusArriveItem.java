package org.techtown.testapi_2;

public class BusArriveItem {
    private String plateNo1;
    private String locationNo1;
    private String plateNo2;
    private String locationNo2;

    public BusArriveItem() {
    }

    public BusArriveItem(String plateNo1, String locationNo1, String plateNo2, String locationNo2) {
        this.plateNo1 = plateNo1;
        this.locationNo1 = locationNo1;
        this.plateNo2 = plateNo2;
        this.locationNo2 = locationNo2;
    }

    public String getPlateNo1() {
        return plateNo1;
    }

    public void setPlateNo1(String plateNo1) {
        this.plateNo1 = plateNo1;
    }

    public String getLocationNo1() {
        return locationNo1;
    }

    public void setLocationNo1(String locationNo1) {
        this.locationNo1 = locationNo1;
    }

    public String getPlateNo2() {
        return plateNo2;
    }

    public void setPlateNo2(String plateNo2) {
        this.plateNo2 = plateNo2;
    }

    public String getLocationNo2() {
        return locationNo2;
    }

    public void setLocationNo2(String locationNo2) {
        this.locationNo2 = locationNo2;
    }
}
