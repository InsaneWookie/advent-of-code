const Heap = require('heap');
const path = require('path');
const fs = require('fs');

const input = fs
	.readFileSync(path.join(__dirname, 'input.txt'), 'utf8')
	.toString()
	.trim();

/**
 * @typedef {String} GridId - Two numbers separated by a comma.
 * @example "10,5"
 */

/**
 * @typedef {Object} InfiniteGridConstructorOptions
 * @property {?Function<x, y>} defaultFactory - Defaults to returning 0 for new coords
 * @property {?Object} string_map - Map grid values to strings.
 * @property {?String|any[][]} load - Initial grid to load. Can be a "2D" string (string with new lines), or a "2D" array.
 * @property {?Function<v>} parseAs - When `load` is defined, this parses the cell in the split string. Defaults to `String`.
 */

class InfiniteGrid {
	/**
	 * @param {InfiniteGridConstructorOptions} options
	 */
	constructor({ defaultFactory = (x, y) => 0, string_map = {}, load, parseAs } = {}) {
		this.defaultFactory = defaultFactory.bind(this);
		this.string_map = string_map;
		this.grid = new Map();
		this.max_x = -Infinity;
		this.min_x = Infinity;
		this.max_y = -Infinity;
		this.min_y = Infinity;

		if (load) {
			this.load(load, parseAs);
		}
	}

	/**
	 * @param {Number} x
	 * @param {Number} y
	 * @returns {GridId}
	 */
	static toId(x, y) {
		return `${x},${y}`;
	}

	/**
	 * @param {GridId} id
	 * @param {Boolean} [return_as_object=false]
	 * @returns {{x: Number, y: Number} | [Number, Number]}
	 */
	static toCoords(id, return_as_object = false) {
		let [x, y] = id.split(',');
		x = parseInt(x, 10);
		y = parseInt(y, 10);
		return return_as_object ? { x, y } : [x, y];
	}

	/**
	 * @param {String} two_dimensional_string
	 * @returns {any[][]}
	 */
	static split(two_dimensional_string) {
		return two_dimensional_string.split('\n').map((row) => row.split(''));
	}

	reset() {
		this.grid = new Map();
		this.max_x = -Infinity;
		this.min_x = Infinity;
		this.max_y = -Infinity;
		this.min_y = Infinity;
		return this;
	}

	/**
	 * @param {String|any[][]} input
	 */
	load(input, parseAs = String) {
		this.reset();
		let grid = input;
		if (typeof input === 'string') {
			grid = InfiniteGrid.split(input);
		}

		for (let y = 0; y < grid.length; y++) {
			for (let x = 0; x < grid[y].length; x++) {
				this.set(x, y, parseAs(grid[y][x]));
			}
		}
	}

	/**
	 * @param {Number} x
	 * @param {Number} y
	 * @param {Boolean} [diagonals=false]
	 * @returns {Map} Return a map with optional keys N, W, E, S (and NW, NE, SW, SE if `diagonals` is true) if those neighbors are within the bounds of the map.
	 */
	neighbors(x, y, diagonals = false) {
		const neighboring_cells = new Map();
		if (!this.inBounds(x, y)) {
			return neighboring_cells;
		}

		const neighbors_lookup = [
			['N', [x, y - 1]],
			['W', [x - 1, y]],
			['E', [x + 1, y]],
			['S', [x, y + 1]],
		];

		if (diagonals) {
			neighbors_lookup.push(
				['NW', [x - 1, y - 1]],
				['NE', [x + 1, y - 1]],
				['SW', [x - 1, y + 1]],
				['SE', [x + 1, y + 1]]
			);
		}

		for (let [key, coord] of neighbors_lookup) {
			let [cx, cy] = coord;
			if (this.inBounds(cx, cy)) {
				neighboring_cells.set(key, {
					id: InfiniteGrid.toId(cx, cy),
					coord,
					value: this.get(cx, cy),
				});
			}
		}

		return neighboring_cells;
	}

	/**
	 * @param {Number} x
	 * @param {Number} y
	 * @param {any} value
	 */
	set(x, y, value) {
		if (typeof x !== 'number' || typeof y !== 'number') {
			throw new Error(`x and y must be numbers, got (${typeof x})${x} and (${typeof y})${y}`);
		}
		if (x < this.min_x) this.min_x = x;
		if (x > this.max_x) this.max_x = x;
		if (y < this.min_y) this.min_y = y;
		if (y > this.max_y) this.max_y = y;
		const id = InfiniteGrid.toId(x, y);
		this.grid.set(id, value);
	}

	/**
	 * @param {Number} x
	 * @param {Number} y
	 * @returns {any}
	 */
	get(x, y) {
		const id = InfiniteGrid.toId(x, y);
		if (!this.grid.has(id)) {
			this.set(x, y, this.defaultFactory(x, y));
		}
		return this.grid.get(id);
	}

	/**
	 * @param {RegExp|any} value
	 * @param {Boolean} [as_coords] - When true, returns a Map of `[x, y]` number values, otherwise returns a Map of string IDs.
	 * @returns {Array<[any,GridId|Coord]>} - Returns an Array, the first value matching the cell found, and the 2nd the coords or ID.
	 */
	findAll(value, as_coords = true) {
		const found = [];
		for (let [id, cell] of this.grid) {
			const check = value instanceof RegExp ? value.test(cell) : value === cell;
			if (check) {
				found.push([cell, as_coords ? InfiniteGrid.toCoords(id) : id]);
			}
		}

		return found;
	}

	inBounds(x, y) {
		return x >= this.min_x && x <= this.max_x && y >= this.min_y && y <= this.max_y;
	}

	clone({ empty = false } = {}) {
		const infinite_grid_clone = new InfiniteGrid();
		const new_map = new Map();
		if (!empty) {
			for (let [key, val] of this.grid) {
				new_map.set(key, typeof val === 'object' ? JSON.parse(JSON.stringify(val)) : val);
			}
		}
		infinite_grid_clone.defaultFactory = this.defaultFactory.bind(this);
		infinite_grid_clone.string_map = JSON.parse(JSON.stringify(this.string_map));
		infinite_grid_clone.grid = new_map;
		infinite_grid_clone.max_x = this.max_x;
		infinite_grid_clone.min_x = this.min_x;
		infinite_grid_clone.max_y = this.max_y;
		infinite_grid_clone.min_y = this.min_y;

		return infinite_grid_clone;
	}

	sum() {
		let sum = 0;
		for (let value of this.grid.values()) {
			sum += value;
		}

		return sum;
	}

	resize() {
		this.max_x = -Infinity;
		this.min_x = Infinity;
		this.max_y = -Infinity;
		this.min_y = Infinity;

		for (let id of this.grid.keys()) {
			let [x, y] = InfiniteGrid.toCoords(id);
			if (x < this.min_x) this.min_x = x;
			if (x > this.max_x) this.max_x = x;
			if (y < this.min_y) this.min_y = y;
			if (y > this.max_y) this.max_y = y;
		}
	}

	buildDijkstrasFrontier(from_x, from_y) {
		const from_id = InfiniteGrid.toId(from_x, from_y);

		// Sort our frontier by its priority, so we pick nodes to visit that have the lowest cost.
		const frontier = new Heap((node_a, node_b) => node_a.priority - node_b.priority);
		frontier.push({ id: from_id, priority: 0 });

		const came_from = new Map([[from_id, null]]);
		const cost_so_far = new Map([[from_id, 0]]);
		while (!frontier.empty()) {
			const current = frontier.pop();

			const [current_x, current_y] = InfiniteGrid.toCoords(current.id);

			for (let next of this.neighbors(current_x, current_y).values()) {
				const new_cost = cost_so_far.get(current.id) + next.value;
				if (!cost_so_far.has(next.id) || new_cost < cost_so_far.get(next.id)) {
					cost_so_far.set(next.id, new_cost);
					frontier.push({ id: next.id, priority: new_cost });
					came_from.set(next.id, current.id);
				}
			}
		}

		return came_from;
	}

	getShortestWeightedPath(from_x, from_y, to_x, to_y, { include_from = true } = {}) {
		const from_id = InfiniteGrid.toId(from_x, from_y);
		const to_id = InfiniteGrid.toId(to_x, to_y);
		const came_from = this.buildDijkstrasFrontier(from_x, from_y);
		let current = to_id;

		let path = [];
		while (current !== from_id) {
			path.push(current);
			current = came_from.get(current);
		}

		if (include_from) {
			path.push(from_id);
		}
		path.reverse();
		return path;
	}

	toGrid() {
		let grid = [];
		for (let y = this.min_y; y <= this.max_y; y++) {
			let row = [];
			for (let x = this.min_x; x <= this.max_x; x++) {
				let cell = this.get(x, y);
				row.push(cell);
			}
			grid.push(row);
		}

		return grid;
	}

	toString() {
		let grid = this.toGrid();
		let rows = '';
		for (let y = 0; y < grid.length; y++) {
			let row = '';
			for (let x = 0; x < grid[y].length; x++) {
				let cell = grid[y][x];
				let cell_string = cell in this.string_map ? this.string_map[cell] : String(cell);
				row += cell_string;
			}
			rows += rows.length ? '\n' + row : row;
		}

		return rows;
	}

	*[Symbol.iterator]() {
		yield* this.grid.entries();
	}
}

function partOne() {
	const grid = new InfiniteGrid({
		load: input,
		parseAs: Number,
	});

	const path = grid.getShortestWeightedPath(0, 0, grid.max_x, grid.max_y, {
		include_from: false,
	});
	const path_sum = path.reduce((sum, cell_id) => sum + grid.grid.get(cell_id), 0);

	console.log(path_sum);
}

function partTwo() {
	let grid = new InfiniteGrid({
		load: input,
		parseAs: Number,
	});

	function increaseGrid(grid) {
		let new_grid = grid.clone({ empty: true });
		for (let [id, value] of grid) {
			let new_vale = value + 1;
			if (new_vale > 9) {
				new_vale = 1;
			}

			new_grid.grid.set(id, new_vale);
		}

		return new_grid;
	}

	function concatGrids(...grids) {
		let rows = [];
		for (let i = 0; i < grids.length; i++) {
			let grid = grids[i];
			let inner_rows = grid.toString().split('\n');
			for (let j = 0; j < inner_rows.length; j++) {
				if (rows[j] === undefined) {
					rows[j] = '';
				}
				// Concat individual rows across
				rows[j] += inner_rows[j];
			}
		}

		// Finally join all rows together
		return rows.join('\n');
	}

	/**
	 * If I have a grid `a`, and if I "increase that grid by 1" to give me `b`, and increasing
	 * `b` by 1 gives me `c`, etc., then expanding a grid to be 3x3 gives me a
	 * new grid that looks like:
	 *
	 *     a b c
	 *     b c d
	 *     c d e
	 *
	 * You can see that subgrid `b` is copied "diagonally," and similarly for `c` and `d`.
	 *
	 * So I don't have to make unique grids for each cell. In this 3x3 expanded grid, I only have
	 * 5 unique grids [a, b, c, d, e] among the 9 cells.
	 */
	function buildGridOutDiagonally(grid, copies = 5) {
		let row_of_grids = [grid];
		// The first row requires the most amount of work, because we only get to reuse the first cell
		for (let x = 1; x < copies; x++) {
			let last_col = row_of_grids[row_of_grids.length - 1];
			row_of_grids.push(increaseGrid(last_col));
		}

		let rows = [row_of_grids];

		// After that, we can reuse all except 1 of the previously generated grids in the next row
		for (let y = 1; y < copies; y++) {
			let last_row = rows[rows.length - 1];
			let last_col = last_row[last_row.length - 1];

			// Copy all grids except the first one
			row_of_grids = last_row.slice(1);
			row_of_grids.push(increaseGrid(last_col));

			rows.push(row_of_grids);
		}

		/**
		 * Now concatenate all rows to create wide "1 col" row strings,
		 * then join on '\n' to create a GIANT grid we can load into a new grid.
		 */
		const giant_grid_input = rows.map((row) => concatGrids(...row)).join('\n');

		// Load this new input to create our new larger grid to pathfind on
		return new InfiniteGrid({
			load: giant_grid_input,
			parseAs: Number,
		});
	}

	const giant_grid = buildGridOutDiagonally(grid, 5);

	// Same steps as before
	const path = giant_grid.getShortestWeightedPath(0, 0, giant_grid.max_x, giant_grid.max_y, {
		include_from: false,
	});
	const path_sum = path.reduce((sum, cell_id) => sum + giant_grid.grid.get(cell_id), 0);

	console.log(path_sum);
}

partOne();
partTwo();
