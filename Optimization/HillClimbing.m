function [result timeToConverge] = HillClimbing(f, x0, y0, epsilon)
	timeToConverge = 0;
	while true
		timeToConverge = timeToConverge + 1; 
		fValue = f(x0, y0);
		[nx, ny] = findNeighbours(x0, y0, epsilon);

		%if no neighbour within domain, return
		if(length(nx) == 0 || length(ny) == 0)
			break
		end
		[xi, yi, fi] = findMax(f, nx, ny);
		if(fi > fValue)
			x0 = xi;
			y0 = yi;
		else
			% find local mixmum, return
			break;
		end 
	end 
	result = [x0 y0 f(x0, y0)];
end

% find the max function value among x y pairs
function [maxX, maxY, maxValue] = findMax(f, x, y)
	fValues = f(x, y);
	[maxValue, maxIndex] = max(fValues);
	maxX = x(maxIndex);
	maxY = y(maxIndex);
end
