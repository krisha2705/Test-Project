package org.barclays.bfg.mappingfiles.codequality.cli;

/**
 * The Class CLIConfig.
 */
public class CLIConfig {


	/** The mapping file. */
	private final String mappingFile;


	/**
	 * Instantiates a new CLI config.
	 *
	 * @param pathToConfig the path to config
	 * @param mappingFile the mapping file
	 */
	public CLIConfig(String pathToConfig, String mappingFile) {
        this.pathToConfig = pathToConfig;
        this.mappingFile = mappingFile;
    }

    /**
 * The Enum Format.
 */
public enum Format {
	/** The csv. */		CSV,
	/** The json. */	JSON 
	};

        
    /** The path to config. */
    private final String pathToConfig;

   
    /**
     * Gets the path to config.
     *
     * @return the path to config
     */
    public String getPathToConfig() {
        return pathToConfig;
    }
    


    
    /**
     * Gets the maping file.
     *
     * @return the maping file
     */
    public String getMapingFile() {
        return mappingFile;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format(" config: %s", pathToConfig);
    }
}
