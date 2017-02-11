function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%
pos_pts = X(y==1, :);
neg_pts = X(y==0, :);
plot(pos_pts(:, 1), pos_pts(:, 2), 'k+r');
plot(neg_pts(:, 1), neg_pts(:, 2), 'kob');
% =========================================================================



hold off;

end