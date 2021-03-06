/*
 * Copyright (C) 2011 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */

package gov.nasa.worldwind.symbology.milstd2525.graphics.firesupport.areas;

import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.render.*;

import java.util.*;

/**
 * Implementation of the Position Area for Artillery, Rectangular graphic (2.X.4.3.2.6.1).
 *
 * @author pabercrombie
 * @version $Id$
 */
public class RectangularPositionArea extends AbstractRectangularGraphic
{
    /** Function ID for the Position Area for Artillery graphic (2.X.4.3.2.6.1). */
    public final static String FUNCTION_ID = "ACPR--";

    /** Create a new target. */
    public RectangularPositionArea()
    {
        super();
    }

    /** Create labels for the start and end of the path. */
    @Override
    protected void createLabels()
    {
        // This graphic has labels at the top, bottom, left, and right of the quad.
        this.addLabel("PAA");
        this.addLabel("PAA");
        this.addLabel("PAA");
        this.addLabel("PAA");
    }

    @Override
    protected void determineLabelPositions(DrawContext dc)
    {
        Iterable<? extends LatLon> corners = this.quad.getLocations(dc.getGlobe());
        if (corners == null)
            return;

        Iterator<? extends LatLon> iterator = corners.iterator();

        // Position the labels at the midpoint of each side of the quad.
        int i = 0;
        LatLon locA = iterator.next();
        while (iterator.hasNext() && i < this.labels.size())
        {
            LatLon locB = iterator.next();

            // Find the midpoint of the two corners
            LatLon mid = LatLon.interpolateGreatCircle(0.5d, locA, locB);

            // Position the label at the midpoint.
            this.labels.get(i).setPosition(new Position(mid, 0));

            locA = locB;
            i += 1;
        }
    }
}