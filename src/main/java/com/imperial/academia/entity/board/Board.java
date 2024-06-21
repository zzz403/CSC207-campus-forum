package com.imperial.academia.entity.board;

import java.sql.Timestamp;

/**
 * The Board class represents a discussion board entity with relevant details.
 */
public class Board {
    private int id;
    private String name;
    private String description;
    private int creatorId;
    private Timestamp creationDate;
    private Timestamp lastModified;

    /**
     * Constructs a new Board with the specified details.
     * 
     * @param id The unique identifier of the board.
     * @param name The name of the board.
     * @param description The description of the board.
     * @param creatorId The ID of the user who created the board.
     * @param creationDate The timestamp when the board was created.
     * @param lastModified The timestamp when the board was last modified.
     */
    public Board(int id, String name, String description, int creatorId, Timestamp creationDate, Timestamp lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the board.
     * 
     * @return The unique identifier of the board.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Sets the unique identifier of the board.
     * 
     * @param id The unique identifier of the board.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Gets the name of the board.
     * 
     * @return The name of the board.
     */
    public String getName() { 
        return name; 
    }

    /**
     * Sets the name of the board.
     * 
     * @param name The name of the board.
     */
    public void setName(String name) { 
        this.name = name; 
    }

    /**
     * Gets the description of the board.
     * 
     * @return The description of the board.
     */
    public String getDescription() { 
        return description; 
    }

    /**
     * Sets the description of the board.
     * 
     * @param description The description of the board.
     */
    public void setDescription(String description) { 
        this.description = description; 
    }

    /**
     * Gets the ID of the user who created the board.
     * 
     * @return The ID of the user who created the board.
     */
    public int getCreatorId() { 
        return creatorId; 
    }

    /**
     * Sets the ID of the user who created the board.
     * 
     * @param creatorId The ID of the user who created the board.
     */
    public void setCreatorId(int creatorId) { 
        this.creatorId = creatorId; 
    }

    /**
     * Gets the timestamp when the board was created.
     * 
     * @return The timestamp when the board was created.
     */
    public Timestamp getCreationDate() { 
        return creationDate; 
    }

    /**
     * Sets the timestamp when the board was created.
     * 
     * @param creationDate The timestamp when the board was created.
     */
    public void setCreationDate(Timestamp creationDate) { 
        this.creationDate = creationDate; 
    }

    /**
     * Gets the timestamp when the board was last modified.
     * 
     * @return The timestamp when the board was last modified.
     */
    public Timestamp getLastModified() { 
        return lastModified; 
    }

    /**
     * Sets the timestamp when the board was last modified.
     * 
     * @param lastModified The timestamp when the board was last modified.
     */
    public void setLastModified(Timestamp lastModified) { 
        this.lastModified = lastModified; 
    }
}
