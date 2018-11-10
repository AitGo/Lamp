package com.rbu.lamphouse.diagnose;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/7 16:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class BluetoothEntity {

    private String bluetoothName;

    private String bluetoothMac;

    public String getBluetoothName() {
        return bluetoothName;
    }

    public void setBluetoothName(String bluetoothName) {
        this.bluetoothName = bluetoothName;
    }

    public String getBluetoothMac() {
        return bluetoothMac;
    }

    public void setBluetoothMac(String bluetoothMac) {
        this.bluetoothMac = bluetoothMac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BluetoothEntity entity = (BluetoothEntity) o;

        return bluetoothMac.equals(entity.bluetoothMac);

    }

    @Override
    public int hashCode() {
        int result = bluetoothMac.hashCode();
        result = 31 * result + bluetoothName.hashCode();
        return result;
    }
}
