package com.chess.engine.board;
import java.util.HashMap;
import java.util.Map;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {
	protected final int tileCoordinate;
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		for(int i=0; i<BoardUtils.NUM_TILES; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
	private Tile(final int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	
	public static Tile createTile(final int tileCoordinate, final Piece piece) {
		return piece !=null? new OccupiedTile(tileCoordinate, piece): EMPTY_TILES_CACHE.get(tileCoordinate);
	}
	
	public int getTileCoordinate() {
		return this.tileCoordinate;
	}
	
	public abstract boolean isTileOccupied();
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile {
		EmptyTile(final int coordinate) {
			super(coordinate);
		}
		@Override
		public String toString() {
			return "-";
		}

		@Override
		public boolean isTileOccupied() {
			return false;
		}

		@Override
		public Piece getPiece() {
			return null;
		}
	}
	
	public static final class OccupiedTile extends Tile {
		
		private final Piece pieceOnTile;
		
		OccupiedTile(int coordinate, final Piece piece) {
			super(coordinate);
			this.pieceOnTile = piece;
		}
		
		@Override
		public String toString() {
			return pieceOnTile.getPieceAlliance().isBlack()? pieceOnTile.toString().toLowerCase() : pieceOnTile.toString();
		}
		
		
		@Override
		public boolean isTileOccupied() {
			return true;
		}
		@Override
		public Piece getPiece() {
			return pieceOnTile;
		}
	}

	
}




