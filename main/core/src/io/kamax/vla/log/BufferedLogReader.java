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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BufferedLogReader implements _LogReader {

   private URL location;
   private boolean finished = false;
   private BufferedReader bf;
   private List<String> lines = new ArrayList<String>();

   @Override
   public void setLocation(URL location) {
      this.location = location;
   }

   @Override
   public List<String> get() throws IOException {
      if (finished) {
         return lines;
      }

      bf = new LineNumberReader(new InputStreamReader(location.openStream()));
      try {


         String line;
         while ((line = bf.readLine()) != null) {
            lines.add(line);
         }

         return lines;
      } finally {
         finished = true;
         bf.close();
      }
   }

}
