/*
 * VBoxLA - VirtualBox Log Analyzer
 * Copyright (C) 2015 - Maxime Dor
 * 
 * http://kamax.io/vboxla/
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 */

package io.kamax.vla.log;

import java.util.List;

/**
 * Represent a line in the log
 */
public interface _LogLine {

   /**
    * Type of line
    */
   public enum Type {

      /**
       * Default value if the line was not identified by the parser
       */
      Unknown,

      /**
       * If the line contains information
       */
      Info,

      /**
       * If the line contains suggestion or any other user feedback
       */
      Suggestion,

      /**
       * If the line contains a warning
       */
      Warning,

      /**
       * If the line contains an error
       */
      Error;
   }

   /**
    * Type of data this line if made of. Identified by the parser.
    * 
    * @return Type of data
    */
   public Type getType();

   /**
    * Get the line raw text
    * 
    * @return String of the raw text
    */
   public String getText();

   /**
    * Check if the line has a timestamp set
    * 
    * @return True if a timestamp was set, False if not
    */
   public boolean hasTimestamp();

   /**
    * Set an absolute time value for the line which must be greater than 0. Precision is implementation dependent.
    * 
    * @param timestamp long value
    * @throws IllegalArgumentException if value is 0
    */
   public void setTimestamp(long timestamp) throws IllegalArgumentException;

   /**
    * Absolute time value. Precision is implementation dependent.
    * 
    * @return long value or 0 if none is set
    * @see #hasTimestamp()
    */
   public long getTimestamp();

   /**
    * Get the tags applied by the parser or analyzer to this log line
    * 
    * @return List of String for the tags
    */
   public List<String> getTags();

   /**
    * Get the log section to which this line belong. {@link _LogSection#General} by default
    * 
    * @return _LogSection object
    */
   public _LogSection getSection();

}
