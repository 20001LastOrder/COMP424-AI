function o= problem1()
	% find f1 
	doHillClimbing('F1', @f1,1);
	doHillClimbing('F2', @f2,2);
end

function out = doHillClimbing(name, f, figureID)
	resultF = zeros(100,3);
	timeToConvergeF = zeros(100, 1);
	for i=1:100
		x = rand()*10;
		y = rand()*10;
		[resultF(i, :) timeToConvergeF(i)] = HillClimbing(f, x, y, 0.01);
	end
	%resultF(101,:) = [0 std(resultF(:,3)) mean(resultF(:, 3))];
	%disp('F1 data: '), disp(resultF)
	t = 1:1:100;
	figure(figureID);stem(t,resultF(:,3));
	disp(strcat(name, 'result std: ')), disp(std(resultF(:,3)));
	disp(strcat(name, 'result mean: ')), disp(mean(resultF(:,3)));
	disp(strcat(name, 'converge std: ')), disp(std(timeToConvergeF));
	disp(strcat(name, 'converge std: ')), disp(mean(timeToConvergeF)); 
end
% resultF2 = zeros(101,3);
% for i=1:100
% 	x = rand()*10;
% 	y = rand()*10;
% 	resultF2(i, :) = HillClimbing(@f2, x, y, 0.01);
% end

% resultF2;
% resultF2(101,:) = [0 std(resultF2(:,3)) mean(resultF2(:, 3))];

% disp('F2 data: '), disp(resultF2)
