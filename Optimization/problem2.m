function o= problem2()
	% find f1 
	beamWidth = [2; 4; 8; 16];

	for i = 1:length(beamWidth)
		doHillClimbing(strcat('F2 with beam width ', num2str(beamWidth(i)), ': '), @f2, beamWidth(i), 0.01, i);
	end 
end

function out = doHillClimbing(name, f, beamWidth, epsilon,figureID)
	resultF = zeros(100,3);
	timeToConvergeF = zeros(100, 1);
	for i=1:100
		x = rand(beamWidth, 1) * 10;
		y = rand(beamWidth, 1) * 10;
		[resultF(i, :) timeToConvergeF(i)] = LocalBeaming(f, x, y, 0.01);
	end
	%resultF(101,:) = [0 std(resultF(:,3)) mean(resultF(:, 3))];
	%disp('F1 data: '), disp(resultF)
	t = 1:1:100;
	figure(figureID);stem(t,resultF(:,3));
	title(strcat(name, " final value"));
	figure(10*figureID); stem(t,timeToConvergeF);
	title(strcat(name, " step to converge"));
	disp(strcat(name, 'result std: ')), disp(std(resultF(:,3)));
	disp(strcat(name, 'result mean: ')), disp(mean(resultF(:,3)));
	disp(strcat(name, 'converge std: ')), disp(std(timeToConvergeF));
	disp(strcat(name, 'converge mean: ')), disp(mean(timeToConvergeF)); 
end