package edu.ieselgrao.javadoc;
import java.time.LocalDate;

public class Planet
{
    // Constants for error messages
    public static final String INVALID_NAME = "[ERROR] Name cannot be null or empty";
    public static final String INVALID_NUMBER_OF_MOONS = "[ERROR] Number of moons cannot be negative";
    public static final String INVALID_MASS = "[ERROR] Mass cannot be less than 10e23 kg";
    public static final String INVALID_RADIUS = "[ERROR] Radius cannot be less than 500 km";
    public static final String INVALID_GRAVITY = "[ERROR] Gravity cannot be negative or zero";
    public static final String INVALID_LAST_ALBEDO_MEASUREMENT = "[ERROR] Last albedo measurement cannot be null or in the future";
    public static final String INVALID_PLANET_TYPE = "[ERROR] Invalid planet type";

    // Constants for minimum values
    private static final double MIN_MASS = 5.97e22;
    private static final double MIN_RADIUS = 500;

    // Attributes
    private String name;
    private int numberOfMoons;
    private double mass;
    private double radius;
    private double gravity;
    private LocalDate lastAlbedoMeasurement;
    private boolean hasRings;
    private Atmosphere atmosphere;
    private PlanetType type;

    /**
     * Constructor básico de la clase Planet
     * @param name                      name of the planet
     * @param numberOfMoons             number of moons
     * @param mass                      mass of the planet
     * @param radius                    radium  of the planet
     * @param gravity                   gravity of the planet
     * @param lastAlbedoMeasurement     date of the last albedo's measure
     * @param hasRings                  it throws if the planet has rings
     * @param type                      type of planet
     */
    // Basic constructor
    public Planet(String name, int numberOfMoons, double mass, double radius, double gravity, LocalDate lastAlbedoMeasurement, boolean hasRings, PlanetType type)
    {
        setName(name);
        setNumberOfMoons(numberOfMoons);
        setMass(mass);
        setRadius(radius);
        setGravity(gravity);
        setLastAlbedoMeasurement(lastAlbedoMeasurement);
        setHasRings(hasRings);
        setType(type);

        atmosphere = null;
    }

    /**
     * Detailed constructor for the Planet class. If the atmospheric data is invalid,
     * it will be set to null.
     * @param name                      Name of the planet
     * @param numberOfMoons             Number of moons
     * @param mass                      Mass of the planet
     * @param radius                    Radius of the planet
     * @param gravity                   Gravity of the planet
     * @param lastAlbedoMeasurement     Date of the last albedo measurement
     * @param hasRings                  Indicates whether it has rings
     * @param type                      Type of planet
     * @param composition               Atmospheric composition
     * @param lastObservation           Date of the last observation
     * @param airQuality                Air quality
     * @param pressure                  Atmospheric pressure
     * @param density                   Atmospheric density
     * @param hasClouds                 Indicates whether there are clouds
     */
    // Detailed constructor with atmosphere
    public Planet(String name, int numberOfMoons, double mass, double radius, double gravity, LocalDate lastAlbedoMeasurement, boolean hasRings, PlanetType type, String composition, LocalDate lastObservation, int airQuality, double pressure, double density, boolean hasClouds)
    {
        setName(name);
        setNumberOfMoons(numberOfMoons);
        setMass(mass);
        setRadius(radius);
        setGravity(gravity);
        setLastAlbedoMeasurement(lastAlbedoMeasurement);
        setHasRings(hasRings);
        setType(type);

        try
        {
            setAtmosphere(composition, lastObservation, airQuality, pressure, density, hasClouds);
        }
        catch (IllegalArgumentException e)
        {
            this.atmosphere = null;
        }
    }

    // Getters and setters
    public String getName()
    {
        return name;
    }

    /**
     * established the name using the get
     * @param name                          name of the earth
     * @throws IllegalArgumentException     throws an exception if it's null or empty
     */
    public void setName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    public int getNumberOfMoons()
    {
        return numberOfMoons;
    }

    /**
     * establish number of moons
     * @param numberOfMoons                 number of moons
     * @throws IllegalArgumentException     throws an exception if is negative
     */
    public void setNumberOfMoons(int numberOfMoons)
    {
        if (numberOfMoons < 0)
        {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_MOONS);
        }
        this.numberOfMoons = numberOfMoons;
    }

    public double getMass()
    {
        return mass;
    }

    /**
     * establish the mass of the earth
     * @param mass                          ,ass in kilograms
     * @throws IllegalArgumentException     throws an exception if is lower than the accurate weight
     */
    public void setMass(double mass)
    {
        if (mass < MIN_MASS)
        {
            throw new IllegalArgumentException(INVALID_MASS);
        }
        this.mass = mass;
    }

    public double getRadius()
    {
        return radius;
    }

    /**
     * establish the radium of the earth
     * @param radius                        radium in kilograms
     * @throws IllegalArgumentException     throws an exception if is lower than 500 km
     */
    public void setRadius(double radius)
    {
        if (radius < MIN_RADIUS)
        {
            throw new IllegalArgumentException(INVALID_RADIUS);
        }
        this.radius = radius;
    }

    public double getGravity()
    {
        return gravity;
    }

    /**
     * establish the gravity of the earth
     * @param gravity                       gravity values
     * @throws IllegalArgumentException     throws an exception if is lower than 0 or equals
     */
    public void setGravity(double gravity)
    {
        if (gravity <= 0)
        {
            throw new IllegalArgumentException(INVALID_GRAVITY);
        }
        this.gravity = gravity;
    }

    public LocalDate getLastAlbedoMeasurement()
    {
        return lastAlbedoMeasurement;
    }

    /**
     * establish the last albedo's measure
     * @param lastAlbedoMeasurement          measure date
     * @throws IllegalArgumentException     throws an exception if is null or higher that the current date
     */
    public void setLastAlbedoMeasurement(LocalDate lastAlbedoMeasurement)
    {
        // last albedo measurement is allowed to be today (LocalDate.now())
        if (lastAlbedoMeasurement == null || lastAlbedoMeasurement.isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException(INVALID_LAST_ALBEDO_MEASUREMENT);
        }
        this.lastAlbedoMeasurement = lastAlbedoMeasurement;
    }

    public boolean hasRings()
    {
        return hasRings;
    }

    /**
     * tells if the planet has ring
     * @param hasRings  true if it has rings and false if  it doesn't
     */
    public void setHasRings(boolean hasRings)
    {
        this.hasRings = hasRings;
    }
    public Atmosphere getAtmosphere()
    {
        return atmosphere;
    }

    /**
     * Creates and assigns an atmosphere to the planet; may throw an exception
     * if the provided data is invalid.
     * @param composition       Atmospheric composition
     * @param lastObservation   Observation date
     * @param airQuality        Air quality
     * @param pressure          Atmospheric pressure
     * @param density           Atmospheric density
     * @param hasClouds         Presence of clouds
     */
    public void setAtmosphere(String composition, LocalDate lastObservation, int airQuality, double pressure, double density, boolean hasClouds)
    {
        // Can propagate IllegalArgumentException
        atmosphere = new Atmosphere(composition, lastObservation, airQuality, pressure, density, hasClouds);
    }

    public PlanetType getType()
    {
        return type;
    }

    /**
     * Sets the planet type.
     * @param type                          The type of planet
     * @throws IllegalArgumentException     Throws an exception if the type is null
     */
    public void setType(PlanetType type)
    {
        if (type == null)
        {
            throw new IllegalArgumentException(INVALID_PLANET_TYPE);
        }
        this.type = type;
    }
}
