function [result, timeToConverge] = LocalBeaming(f, x, y, epsilon)
	beamWidth = length(x);
	neighbours = zeros(1,3);
	timeToConverge = 0;
	while true
		timeToConverge = timeToConverge + 1;
		for i = 1:beamWidth
			[nx, ny] = findNeighbours(x(i), y(i), epsilon);
			fValues = f(nx, ny);
			neighbours = [neighbours; nx ny fValues];
		end
		neighbours(1, :) = [];
		currentFValues= f(x, y);
		[currentMax, maxI] = max(currentFValues);
		if(currentMax >= max(neighbours(:, 3)))
			result = [x(maxI) y(maxI) fValues(maxI)];
			break;
		end
		neighbours = sortrows(neighbours, -3);
		x = neighbours(1 : beamWidth, 1);
		y = neighbours(1 : beamWidth, 2);
		%discard other neighbours
		neighbours = zeros(1,3);
	end
end
