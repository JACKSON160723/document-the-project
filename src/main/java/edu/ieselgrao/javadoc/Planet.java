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
     * @param name                      nombre del planeta
     * @param numberOfMoons             número de lunas
     * @param mass                      masa del planeta
     * @param radius                    radio del planeta
     * @param gravity                   gravedad del planeta
     * @param lastAlbedoMeasurement     fecha de la última medición de albedo
     * @param hasRings                  dice si el planeta tiene anillos
     * @param type                      tipo de planeta
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
     * Constructor detallado de la clase Planet, si los datos de la atmósfera no son válidos, se establecerá como null
     * @param name                      nombre del planeta
     * @param numberOfMoons             número de lunas
     * @param mass                      masa del planeta
     * @param radius                    radio del planeta
     * @param gravity                   gravedad del planeta
     * @param lastAlbedoMeasurement     fecha de la última medición de albedo
     * @param hasRings                  indica si tiene anillos
     * @param type                      tipo de planeta
     * @param composition               composición atmosférica
     * @param lastObservation           fecha de última observación
     * @param airQuality                calidad de aire
     * @param pressure                  presión atmosférica
     * @param density                   densidad atmosférica
     * @param hasClouds                 indica si hay nubes
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
     * Establece el nombre del planeta
     * @param name                          nombre del planeta
     * @throws IllegalArgumentException     lanza una excepción si es null o está vacío
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
     * Establece el número de lunas
     * @param numberOfMoons                 número de lunas
     * @throws IllegalArgumentException     lanza una excepcion si es negativo
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
     * Establece la masa del planeta
     * @param mass                          masa en kilogramos
     * @throws IllegalArgumentException     lanza una excepcion si es menor que la masa mínima permitida
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
     * Establece el radio del planeta
     * @param radius                        radio en kilómetros
     * @throws IllegalArgumentException     lanza excepción si es menor que 500 km
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
     * Establece la gravedad del planeta
     * @param gravity                       valor de la gravedad
     * @throws IllegalArgumentException     lanza una excepción si es menor o igual a 0
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
     * Establece la fecha de la última medición de albedo
     * @param lastAlbedoMeasurement         fecha de medición
     * @throws IllegalArgumentException     lanza una excepción si es null o supera la fecha actual
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
     * Dice si el planeta tiene anillos
     * @param hasRings  true si tiene anillos, false si no
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
     * Crea y asigna una atmósfera al planeta y puede lanzar una excepcioń si los datos proporcionados no son válidos
     * @param composition       composición atmosférica
     * @param lastObservation   fecha de observación
     * @param airQuality        calidad del aire
     * @param pressure          presión atmosférica
     * @param density           densidad atmosférica
     * @param hasClouds         presencia de nubes
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
     * Establece el tipo de planeta
     * @param type                          tipo de planeta
     * @throws IllegalArgumentException     lanza una excepción si es null
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
