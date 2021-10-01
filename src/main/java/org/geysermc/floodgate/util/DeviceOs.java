package org.geysermc.floodgate.util;

public enum DeviceOs
{
    UNKNOWN("Unknown"),
    GOOGLE("Android"),
    IOS("iOS"),
    OSX("macOS"),
    AMAZON("Amazon"),
    GEARVR("Gear VR"),
    HOLOLENS("Hololens"),
    UWP("Windows 10"),
    WIN32("Windows x86"),
    DEDICATED("Dedicated"),
    TVOS("Apple TV"),
    PS4("PS4"),
    NX("Switch"),
    XBOX("Xbox One"),
    WINDOWS_PHONE("Windows Phone");

    private static final DeviceOs[] VALUES;
    private final String displayName;

    public static DeviceOs getById(final int id) {
        return (id < DeviceOs.VALUES.length) ? DeviceOs.VALUES[id] : DeviceOs.VALUES[0];
    }

    @Override
    public String toString() {
        return this.displayName;
    }

    private DeviceOs(final String displayName) {
        this.displayName = displayName;
    }

    static {
        VALUES = values();
    }
}
