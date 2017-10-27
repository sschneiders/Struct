/**
 *
 *  ******************************************************************************
 *  MontiCAR Modeling Family, www.se-rwth.de
 *  Copyright (c) 2017, Software Engineering Group at RWTH Aachen,
 *  All rights reserved.
 *
 *  This project is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 3.0 of the License, or (at your option) any later version.
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this project. If not, see <http://www.gnu.org/licenses/>.
 * *******************************************************************************
 */
package de.monticore.lang.monticar.struct._symboltable;

import de.se_rwth.commons.Joiners;
import de.se_rwth.commons.Splitters;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StructModelNameCalculator extends StructModelNameCalculatorTOP {
    @Override
    protected Set<String> calculateModelNamesForStructFieldDefinition(String name) {
        List<String> parts = Splitters.DOT.splitToList(name);
        if (parts.size() < 2) {
            return Collections.emptySet();
        }
        String modelName = Joiners.DOT.join(parts.subList(0, parts.size() - 1));
        HashSet<String> result = new HashSet<>();
        result.add(modelName);
        return result;
    }
}
