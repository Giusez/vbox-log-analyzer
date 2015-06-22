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

import java.util.ArrayList;
import java.util.List;

public class LogLine implements _LogLine {

   private long timestamp = 0;
   private String data = "";
   private Type type = Type.Unknown;
   private _LogSection section = _LogSection.General;
   private List<String> tags = new ArrayList<String>();

   public LogLine(String data) {
      this.data = data;
   }

   @Override
   public String getText() {
      return data;
   }

   @Override
   public boolean hasTimestamp() {
      return timestamp > 0;
   }

   @Override
   public void setTimestamp(long timestamp) {
      if (timestamp == 0) {
         throw new IllegalArgumentException("Timestamp must be greater than 0");
      }

      this.timestamp = timestamp;

   }

   @Override
   public long getTimestamp() {
      return timestamp;
   }

   @Override
   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }

   @Override
   public List<String> getTags() {
      return new ArrayList<String>(tags);
   }

   public void addTag(String tag) {
      tags.add(tag);
   }

   public void removeTag(String tag) {
      tags.remove(tag);
   }

   public void clearTags() {
      tags.clear();
   }

   @Override
   public _LogSection getSection() {
      return section;
   }

   public void setSection(_LogSection section) {
      this.section = section;
   }

}
