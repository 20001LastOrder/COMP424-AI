% find set of neighbout around x, y within domain
function [nx, ny] = findNeighbours(x, y, epsilon)
	neighbours = [x-epsilon y; x+epsilon y; x y-epsilon; x y+epsilon; x+epsilon y+epsilon; ...
				  x+epsilon y-epsilon; x-epsilon y+epsilon; x-epsilon y-epsilon];
	nx = neighbours(:, 1);
	ny = neighbours(:, 2);
	r = 1:length(nx);
	%remove out range neighbours
	xOutI = outRangeCheck(nx(r));
	yOutI = outRangeCheck(ny(r));
	OutI = xOutI + yOutI;
	nx(OutI>=1)=[];
	ny(OutI>=1)=[];
end

function outRange = outRangeCheck(x)
	%not compare x with 0 to consider the round errors
	outRange = (x < -1e-6) .+ (x > 10+1e-6); % work around for the check so that it will be a vector of booleans
end