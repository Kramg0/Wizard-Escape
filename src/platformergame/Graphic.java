package platformergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Graphic {

	PlatformMain pm;

	public Image spikeUp = new ImageIcon(".\\GameImages\\spikeUp.png").getImage();
	public Image spikeDown = new ImageIcon(".\\GameImages\\spikeDown.png").getImage();
	public Image spikeRight = new ImageIcon(".\\GameImages\\spikeRight.png").getImage();
	public Image spikeLeft = new ImageIcon(".\\GameImages\\spikeLeft.png").getImage();
	public Image leftFrame1 = new ImageIcon(".\\GameImages\\leftFrame1.png").getImage();
	public Image leftFrame2 = new ImageIcon(".\\GameImages\\leftFrame2.png").getImage();
	public Image rightFrame1 = new ImageIcon(".\\GameImages\\rightFrame1.png").getImage();
	public Image rightFrame2 = new ImageIcon(".\\GameImages\\rightFrame2.png").getImage();
	public Image BackgroundFrame1 = new ImageIcon(".\\GameImages\\BackgroundFrame1.png").getImage();
	public Image BackgroundFrame2 = new ImageIcon(".\\GameImages\\BackgroundFrame2.png").getImage();
	public Image leftJump = new ImageIcon(".\\GameImages\\leftJump.png").getImage();
	public Image rightJump = new ImageIcon(".\\GameImages\\rightJump.png").getImage();
	public Image how = new ImageIcon(".\\GameImages\\how.png").getImage();
	public Image redFieldInfo = new ImageIcon(".\\GameImages\\redfield-info.png").getImage();

	public Graphic(PlatformMain platform) {

		pm = platform;
	}

	public void graphics(Graphics g) {

		// Menu graphics
		if (pm.enter == false && pm.enterCount == 0) {

			g.setColor(Color.black);
			g.fillRect(0, 0, 1600, 900);
			g.setColor(Color.white);
			g.drawRect(pm.startGame.x, pm.startGame.y, pm.startGame.width, pm.startGame.height);
			g.setFont(new Font("Courier New", 1, 60));
			g.drawString("Start", pm.startGame.x + 60, pm.startGame.y + 65);
			g.drawRect(pm.howToPlay.x, pm.howToPlay.y, pm.howToPlay.width, pm.howToPlay.height);
			g.drawString("How To Play", pm.howToPlay.x + 60, pm.howToPlay.y + 65);
			g.drawImage(how, pm.howX, 0, null);
		}

		// Game graphics
		else {

			// Background
			if (pm.bgTick % 2 == 0) {
				g.drawImage(BackgroundFrame1, (0 - pm.player.x) / 7, 0, null);
			} else {
				g.drawImage(BackgroundFrame2, (0 - pm.player.x) / 7, 0, null);
			}
			g.setColor(Color.blue);
			if (pm.blueScreen.x == 0) {
				g.fillRect(pm.blueScreen.x, pm.blueScreen.y, pm.blueScreen.width, pm.blueScreen.height);
			}
			
			// Last Level
			if (pm.levelCount == 12){
				
				g.setColor(Color.black);
				g.setFont(new Font("Courier New", 1, 40));
				g.drawString("Congratulations! You finished all levels in this demo." , 200, 200);
				g.drawString("Go right to get back to the menu.", 200, 300);
			}

			// redField
			g.setColor(Color.red);
			for (int i = 0; i < pm.redField.size(); i++) {
				g.fillRect(pm.redField.get(i).x, pm.redField.get(i).y, pm.redField.get(i).width,
						pm.redField.get(i).height);
			}

			// Blue block
			g.setColor(Color.blue);
			g.fillRect(pm.block.x, pm.block.y, pm.block.width, pm.block.height);

			// Obstacles outline
			g.setColor(Color.black);
			for (int i = 0; i < pm.obstacle.size(); i++) {
				g.fillRoundRect(pm.obstacle.get(i).x, pm.obstacle.get(i).y, pm.obstacle.get(i).width,
						pm.obstacle.get(i).height, 40, 40);

				if (pm.obstacle.get(i).y + pm.obstacle.get(i).height > 800) {
					g.fillRect(pm.obstacle.get(i).x, 790, pm.obstacle.get(i).width, 20);
				}
			}
			g.setColor(Color.black);
			g.fillRect(pm.floor.x, pm.floor.y, pm.floor.width, pm.floor.height);
			g.setColor(Color.white);
			g.fillRect(pm.wall.x, pm.wall.y, pm.wall.width, pm.wall.height);
			g.fillRect(pm.floor.x, pm.floor.y + 5, pm.floor.width, pm.floor.height);
			g.fillRect(pm.roof.x, pm.roof.y, pm.roof.width, pm.roof.height);

			// Obstacles inner
			for (int i = 0; i < pm.obstacle.size(); i++) {
				g.fillRoundRect(pm.obstacle.get(i).x + 4, pm.obstacle.get(i).y + 4, pm.obstacle.get(i).width - 8,
						pm.obstacle.get(i).height - 8, 40, 40);

				if (pm.obstacle.get(i).y + pm.obstacle.get(i).height > 800) {
					g.fillRect(pm.obstacle.get(i).x + 4, 780, pm.obstacle.get(i).width - 8, 40);
				}
			}

			// Spikes
			g.setColor(Color.red);
			for (int i = 0; i < pm.spikes.size(); i++) {

				for (int j = 0; j * 33 < pm.spikes.get(i).width; j++) {
					if (pm.spikes.get(i).height == 10) {
						g.drawImage(spikeUp, pm.spikes.get(i).x + 33 * j - 12, pm.spikes.get(i).y - 20, null);
					} else if (pm.spikes.get(i).height == 11) {
						g.drawImage(spikeDown, pm.spikes.get(i).x + 33 * j - 12, pm.spikes.get(i).y - 15, null);
					}
				}

				for (int j = 0; j - 1 < pm.spikes.get(i).height / 33; j++) {
					if (pm.spikes.get(i).width == 10) {
						g.drawImage(spikeLeft, pm.spikes.get(i).x - 20, pm.spikes.get(i).y + 33 * j - 12, null);
					} else if (pm.spikes.get(i).width == 11) {
						g.drawImage(spikeRight, pm.spikes.get(i).x - 15, pm.spikes.get(i).y + 33 * j - 15, null);
					}
				}
				// g.fillRect(spikes.get(i).x, spikes.get(i).y,
				// spikes.get(i).width, spikes.get(i).height);
			}

			// g.fillRect(player.x, player.y, player.width, player.height);

			// Player Animation gibberish...
			if (pm.dir == 0) {
				if (pm.yMotion == 0 && pm.canJump) {
					g.drawImage(rightFrame1, pm.player.x, pm.player.y - 10, null);
				} else {
					g.drawImage(rightJump, pm.player.x, pm.player.y - 10, null);
				}
			} else if (pm.xMotion < 0) {
				if (pm.yMotion == 0 && pm.canJump) {
					if (pm.animTick % 2 == 0) {
						g.drawImage(leftFrame1, pm.player.x - 10, pm.player.y - 10, null);
					} else {
						g.drawImage(leftFrame2, pm.player.x - 10, pm.player.y - 10, null);
					}
				} else {
					g.drawImage(leftJump, pm.player.x - 10, pm.player.y - 10, null);
				}
			} else if (pm.xMotion > 0) {
				if (pm.yMotion == 0 && pm.canJump) {
					if (pm.animTick % 2 == 0) {
						g.drawImage(rightFrame1, pm.player.x, pm.player.y - 10, null);
					} else {
						g.drawImage(rightFrame2, pm.player.x, pm.player.y - 10, null);
					}
				} else {
					g.drawImage(rightJump, pm.player.x, pm.player.y - 10, null);
				}
			} else if (pm.press && pm.dir == 'A') {
				if (pm.yMotion == 0 && pm.canJump) {
					g.drawImage(leftFrame1, pm.player.x - 10, pm.player.y - 10, null);
				} else {
					g.drawImage(leftJump, pm.player.x - 10, pm.player.y - 10, null);
				}
			} else if (pm.press && pm.dir == 'D') {
				if (pm.yMotion == 0 && pm.canJump) {
					g.drawImage(rightFrame1, pm.player.x, pm.player.y - 10, null);
				} else {
					g.drawImage(rightJump, pm.player.x, pm.player.y - 10, null);
				}
			}

			g.setColor(Color.black);
			g.setFont(new Font("Courier New", 1, 20));
			g.drawString("Deaths: " + pm.deathCount, 30, 30);
			g.drawString("Level: " + pm.levelCount, 30, 50);

			if (pm.ticks - pm.clearTicks < 300 && pm.levelCount == 10) {
				g.drawImage(redFieldInfo, 600, 300, null);
			}
		}
	}
}
