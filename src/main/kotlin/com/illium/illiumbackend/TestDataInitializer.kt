package com.illium.illiumbackend

import com.illium.illiumbackend.model.*
import com.illium.illiumbackend.repository.*
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.time.LocalDateTime

@Configuration
class TestDataInitializer {

    @Bean
    fun populateDatabase(techniqueRepository: TechniqueRepository, lessonQueueRepository: LessonQueueRepository, lessonQueueTechniqueRepository: LessonQueueTechniqueRepository, classRepository: EventRepository, eventQueueRepository: EventQueueRepository, recurringRuleRepository: RecurringRuleRepository): CommandLineRunner {
        return CommandLineRunner {
            // Create Techniques Table
            val techniques = listOf(
                Technique(name = "Stances: Neutral Stance, Passive Stance, & Fighting Stance. Movement: Forward, Backward, Sideways Shadow Boxing/Fighting", level = 1),
                Technique(name = "Handstrikes 1: Straight Punches (L/R Combination), Low Punches.", level = 1),
                Technique(name = "Handstrikes 2: Palm Strikes, Hammerfists, Low Punches, Puinches w/ Movement (Advancing/Retreating)", level = 1),
                Technique(name = "Handstrikes 3: Elbows 1-7", level = 1),
                Technique(name = "Kicks 1: Front Kick (Top of Foot/Instep), Front Kick to Vertical Target (Ball of Foot)", level = 1),
                Technique(name = "Kicks 2: Round Kick & Back Kick.", level = 1),
                Technique(name = "Kicks 3: Front Kick (Top of Foot/Instep), Back Kick", level = 1),
                Technique(name = "Knees: Long Knee (No Grab). Knee with Grab (Straight & Round).", level = 1),
                Technique(name = "Combination Attacks: Short Combinations with Hands & Feet. Shadow Boxing/Fighting.", level = 1),
                Technique(name = "Hand Defenses 1: 360° Outside Defenses (Passive Stance), 360° Outside Defenses (with Counter Attack)", level = 1),
                Technique(name = "Hand Defenses 2:  Inside Defense Against Straight & Low Punches", level = 1),
                Technique(name = "Defense Against Shove/Grab: One Hand (Groin), Two Hands (Elbow), Cross Push, Defense Against Shove from Behind, Defense Against Pull From Behind, & Preventative: Educational Block, Throat Poke, Eye Flick", level = 1),
                Technique(name = "Defense Against Shove/Pull: Defense Against Shove from Behind, Defense Against Pull From Behind", level = 1),
                Technique(name = "Choke Defenses 1:  Two Hand Pluck, Extended/Condensed (Front) and One Hand Pluck (Front)", level = 1),
                Technique(name = "Choke Defenses 2: Front with a Push & From Behind", level = 1),
                Technique(name = "Choke Defenses 3: From Side, Extended/Condensed (Static) & From Side, Extended/Condensed (With a Push)", level = 1),
                Technique(name = "Headlocks: Headlock from Side & Headlock from Side with Impending Punch", level = 1),
                Technique(name = "Wrist Releases (Non-Lethal Techniques): Same Side Hand, Opposite Hand, Two Hand Grab, Overhand Grab", level = 1),
                Technique(name = "Ground Fighting 1: Back Fall Break / Side Position, Front Fall Break / Side Position, & Movement", level = 1),
                Technique(name = "Ground Fighting 2: Kicks: Front, Round, Axe, & Side", level = 1),
                Technique(name = "Ground Fighting 3: Getting Up: Figure 4 / Turn & Run, Two Hands, Tactical", level = 1),

                // -- LEVEL 2 (in original appearance order) --
                Technique(name = "Punch Combinations 1-8 & Shadow Boxing (Rolling & Slipping)", level = 2),
                Technique(name = "Kick 1:  Defensive Front Kick (Passive/Advancing), Side Kick (Passive/Advancing/Fighting Stance), & Advancing Back Kick", level = 2),
                Technique(name = "Kicks 2:  Diagonal Roundhouse Kick, Switch Kitch (Switching Heel to Live Side), & Stomps.", level = 2),
                Technique(name = "Punch & Kick Combinations", level = 2),
                Technique(name = "Inside/Outside Defense Against a Punch 1: Inside Defense with Counter vs. Left Punch, Inside Defense with Counter vs. Left Punch (Using Left), & Inside Defense with Counter(s) vs. Right Punch", level = 2),
                Technique(name = "Inside/Outside Defense Against a Punch 2: Defense vs. Hook Punch (Ext/Covering/Body) and Defense Against Uppercut Punch", level = 2),
                Technique(name = "Kick Defenses 1: Defense vs. Low Round Kick (Evasion),  Defense vs. Low Round Kick (Checking), & Defense vs. Low Round Kick (Jamming)", level = 2),
                Technique(name = "Kick Defenses 2:  Outside Stabbing Defense (Ground) & Defense vs. Kick to Ribs (Ground)", level = 2),
                Technique(name = "Kick Defenses 3:  Plucking Defense vs. Side Kick", level = 2),
                Technique(name = "Kick Defense 4: Defense vs. High & Medium Front Kick", level = 2),
                Technique(name = "Choke Defenses 1: Choke Against the Wall from Front", level = 2),
                Technique(name = "Choke Defenses 2:  Shove/Bar-arm with Impending Punches", level = 2),
                Technique(name = "Choke Defenses 3: Choke from Behind (Windpipe/Carotid)", level = 2),
                Technique(name = "Choke Defenses 4: Shove Against the Wall from Behind", level = 2),
                Technique(name = "Bear Hugs 1: Front with Arms Free (With Space/No Space) & Front with Arms Caught (With Space/No Space)", level = 2),
                Technique(name = "Bear Hugs 2:  Behind with Arms Free & Behind with Arms Caught", level = 2),
                Technique(name = "Rolls: Forward Roll & Backward Roll", level = 2),
                Technique(name = "Ground Fighting 1: Defending the Mount (Bottom Pos):  Defending Punches & Escaping (Buck, Trap, & Roll)", level = 2),
                Technique(name = "Ground Fighting 2:  Ground Fighting: Choke While Mounted (Pluck & Buck) & Headlock or Close Choke while Mounted", level = 2),
                Technique(name = "Ground Fighting 3:  The Guard (Bottom Position):  Striking Options from the Guard & Kicking Off from the Guard", level = 2),
                Technique(name = "Ground Fighting 4: Choke Defense While Attacker is in Guard", level = 2),

                // -- LEVEL 3 (in original appearance order) --
                Technique(name = "Head Butt: Forward, Upward, To the Side, From the Back", level = 3),
                Technique(name = "Punches: Ridge Hand & Shoulder/Body Checking", level = 3),
                Technique(name = "Punches: Overhand Right & Overhand Elbow", level = 3),
                Technique(name = "Stances: Neutral Stance, Passive Stance, & Fighting Stance. Movement: Forward, Backward, Sideways Shadow Boxing/Fighting", level = 3),
                Technique(name = "Headlocks: Headlock from Side & Headlock from Side with Impending Punch", level = 3),
                Technique(name = "Stances: Neutral Stance, Passive Stance, & Fighting Stance. Movement: Forward, Backward, Sideways Shadow Boxing/Fighting", level = 3),
                Technique(name = "Kicks: Spinning Back Kick & Kicking in Retreat", level = 3),
                Technique(name = "Sweeps: Foot to Heel & Heel to Heel", level = 3),
                Technique(name = "Punches: Spinning Hammer Fist (Both Attack & Defense) & Spinning Elbow (Both Attack & Defense)", level = 3),
                Technique(name = "Handstrikes 1: Straight Punches (L/R Combination), Low Punches. Hand Defenses: Inside Defense Against Straight & Low Punches.", level = 3),
                Technique(name = "Choke Defenses 3: From Side, Extended/Condensed (Static) & From Side, Extended/Condensed (With a Push)", level = 3),
                Technique(name = "Handstrikes 1: Straight Punches (L/R Combination), Low Punches.", level = 3),
                Technique(name = "Punch Defenses 1: Inside Defense vs. Left/Right Turning Palm, Inside Defense vs. Left/Right: Lean Back & Trap & Lean Back & Kick, Inside Defense vs. Left/Right - Forward Hand/Roll Under, & Left Vertical Punch Block Against Right Cross", level = 3),
                Technique(name = "Kick Defenses 1: General Defense vs. Medium/High Attacks", level = 3),
                Technique(name = "Kicks: Two Straight Knees with a Switch", level = 3),
                Technique(name = "Handstrikes 2: Palm Strikes, Hammerfists, Low Punches, Puinches w/ Movement (Advancing/Retreating)", level = 3),
                Technique(name = "Wrist Releases (Non-Lethal Techniques): Same Side Hand, Opposite Hand, Two Hand Grab, Overhand Grab", level = 3),
                Technique(name = "Handstrikes 2: Palm Strikes, Hammerfists, Low Punches, Puinches w/ Movement (Advancing/Retreating)", level = 3),
                Technique(name = "Punch Defenses 2: Inside Defense vs. Left/Right - Forward Hand/Roll Under, & Left Vertical Punch Block Against Right Cross", level = 3),
                Technique(name = "Kick Defenses 2: Inside Defense with Heel of Hand (Passive/Fight Stance)", level = 3),
                Technique(name = "Headlocks: Headlock from Behind with One Armed Shoulder Throw, Defense vs. Headlock from Side (Spin Inward), & Defense vs. Headlock from Side (Neck Break)", level = 3),
                Technique(name = "Handstrikes 3: Elbows 1-7", level = 3),
                Technique(name = "Kicks 3: Front Kick (Top of Foot/Instep), Back Kick", level = 3),
                Technique(name = "Handstrikes 3: Elbows 1-7", level = 3),
                Technique(name = "Kicks 1: Front Kick (Top of Foot/Instep), Front Kick to Vertical Target (Ball of Foot)", level = 3),
                Technique(name = "Handstrikes 1: Straight Punches (L/R Combination), Low Punches.", level = 3),
                Technique(name = "Kicks 1: Front Kick (Top of Foot/Instep), Front Kick to Vertical Target (Ball of Foot)", level = 3),
                Technique(name = "Kick Defenses 2: Stop Kick - Heel Directed to the Outside (Using Forward Leg) & Stop Kick - Heel Directed to the Inside (Using Back Leg)", level = 3),
                Technique(name = "Bat/Stick Defenses 1: Overhead Swing, Front & Off Angle Left, Overhead Swing Off Angle (Right): Control & Punch & Disarm", level = 3),
                Technique(name = "Knife Defenses 1: Knife Defense vs. Downward Stab, Knife Defense vs. Upward Stab, & Knife Defense vs. Upward Stab to Throat", level = 3),
                Technique(name = "Kicks 2: Round Kick & Back Kick.", level = 3),
                Technique(name = "Handstrikes 2: Palm Strikes, Hammerfists, Low Punches, Puinches w/ Movement (Advancing/Retreating)", level = 3),
                Technique(name = "Kicks 2: Round Kick & Back Kick.", level = 3),
                Technique(name = "Kick Defenses 3: Reflexive Defense vs. Front Kick to Groin & Leg Check", level = 3),
                Technique(name = "Bat/Stick Defenses 2: Baseball Bat to Head & Baseball Bat to Head Reflexive from Reverse Angle", level = 3),
                Technique(name = "Knife Defenses 2: Knife Defense vs. Straight Stab (Dead Side) & Knife Defense vs. Straight Stab (Live Side)", level = 3),
                Technique(name = "Knees: Long Knee (No Grab). Knee with Grab (Straight & Round).", level = 3),
                Technique(name = "Kicks 2: Round Kick & Back Kick.", level = 3),
                Technique(name = "Kicks 3: Front Kick (Top of Foot/Instep), Back Kick", level = 3),
                Technique(name = "Release from Bear Hugs 1: From Front, Lifting (Knees) & From Side (Arms Caught, Arms Free)", level = 3),
                Technique(name = "Bat/Stick Defenses 3: Baseball Bat Stabbing Defense & Baseball Bat Defense from Ground (Facing & Perpendiculuar)", level = 3),
                Technique(name = "Knife Defenses 3: Knife Defense vs. Forward Slash & Knife Defense vs. Backhand Slash", level = 3),
                Technique(name = "Combination Attacks: Short Combinations with Hands & Feet. Shadow Boxing/Fighting.", level = 3),
                Technique(name = "Choke Defenses 2: Front with a Push & From Behind", level = 3),
                Technique(name = "Knees: Long Knee (No Grab). Knee with Grab (Straight & Round).", level = 3),
                Technique(name = "Release from Bear Hugs 2: From Front, Lifting (Knees) & From Behind with Second Attacker from the Front", level = 3),
                Technique(name = "Knife Defenses: Knife Intimidation off the Body from Front, Knife Intimidation Against the Body from Front, Defense vs. Knife Against Body, Behind Elbow (Right/Left)", level = 3),
                Technique(name = "Shotgun 1: Long Gun from the Front (Live Side), Long Gun from Front (Dead Side), Long Gun in Front of Elbow (Right & Left Side)", level = 3),
                Technique(name = "Hand Defenses 1: 360° Outside Defenses (Passive Stance), 360° Outside Defenses (with Counter Attack)", level = 3),
                Technique(name = "Defense Against Shove/Pull: Defense Against Shove from Behind, Defense Against Pull From Behind", level = 3),
                Technique(name = "Combination Attacks: Short Combinations with Hands & Feet. Shadow Boxing/Fighting.", level = 3),
                Technique(name = "Headlock Defense: Reverse Headlock, Guillotine, Standing Position - Swim Through, Throat Poke", level = 3),
                Technique(name = "Hand Gun Defenses 1: Gun to the Front (Body), Gun to Face, Gun to the Chest over Left Side. & Gun to the Head Kneeling (front Front)", level = 3),
                Technique(name = "Shotgun 2: Long Gun from Side Behind Arm (Left/Right) & Long Gun from Behind (Touching)", level = 3),
                Technique(name = "Hand Defenses 2:  Inside Defense Against Straight & Low Punches", level = 3),
                Technique(name = "Knees: Long Knee (No Grab). Knee with Grab (Straight & Round).", level = 3),
                Technique(name = "Hand Defenses 1: 360° Outside Defenses (Passive Stance), 360° Outside Defenses (with Counter Attack)", level = 3),
                Technique(name = "Knife Defenses vs. Charging Knife Attack (Kicks): From the Front, From the Side, & Straight Stab from the Front (Bailout)", level = 3),
                Technique(name = "Hand Gun Defenses 2: Gun to the Side of Head, Not Touching (Right) & Gun to Side of Head, Not Touching (Left)", level = 3),
                Technique(name = "Shotgun 3: Long Gun to the Head Standing & Long Gun to Head Kneeling", level = 3),
                Technique(name = "Defense Against Shove/Grab: One Hand (Groin), Two Hands (Elbow), Cross Push, Defense Against Shove from Behind, Defense Against Pull From Behind, & Preventative: Educational Block, Throat Poke, Eye Flick", level = 3),
                Technique(name = "Hand Defenses 1: 360° Outside Defenses (Passive Stance), 360° Outside Defenses (with Counter Attack)", level = 3),
                Technique(name = "Hand Defenses 2:  Inside Defense Against Straight & Low Punches", level = 3),
                Technique(name = "Ground Fighting 1: Choke from Side (Knee in Belly or Spin Out) & Side Control", level = 3),
                Technique(name = "Hand Gun Defenses 3:  Gun from the Side in Front of the Elbow (Right/Left), Gun from the Side (Behind Left Arm), & Gun from the Side (Behind Right Arm)", level = 3),
                Technique(name = "Shotgun 4: Sawed-Off Shotgun from the Front (Live Side) & Sawed-Off Shotgun from the Front (Dead Side)", level = 3),
                Technique(name = "Choke Defenses 1:  Two Hand Pluck, Extended/Condensed (Front) and One Hand Pluck (Front)", level = 3),
                Technique(name = "Choke Defenses 1:  Two Hand Pluck, Extended/Condensed (Front) and One Hand Pluck (Front)", level = 3),
                Technique(name = "Defense Against Shove/Grab: One Hand (Groin), Two Hands (Elbow), Cross Push, Defense Against Shove from Behind, Defense Against Pull From Behind, & Preventative: Educational Block, Throat Poke, Eye Flick", level = 3),
                Technique(name = "Choke Defenses 2:  Shove/Bar-arm with Impending Punches", level = 3),
                Technique(name = "Ground Fighting 2:  Side Headlock from  Ground - Weight Forward: Palm Strike to Back of Head/Neck & Weight Back: Throat Strike, Leg Sweep", level = 3),
                Technique(name = "Hand Gun Defenses 4:  Gun from Behind (Touching), Gun from Side and Behind at Distance (Left), &  Gun from Side and Behind at Distance (Right)", level = 3),
                Technique(name = "Shotgun 5: Sawed-Off Shotgun from Behind & Sawed-Off Shotgun from Right/Left Side (Front)", level = 3),
                Technique(name = "Choke Defenses 2: Front with a Push & From Behind", level = 3),
                Technique(name = "Combination Attacks: Short Combinations with Hands & Feet. Shadow Boxing/Fighting.", level = 3),
                Technique(name = "Defense Against Shove/Pull: Defense Against Shove from Behind, Defense Against Pull From Behind", level = 3),
                Technique(name = "Choke Defenses 4: Shove Against the Wall from Behind", level = 3),
                Technique(name = "Using Appropriate Combatives for Appropriate Range & Multiple Attackers - 2 on 1", level = 3),
                Technique(name = "Hand Gun Defenses 5: Gun to the Front (Body) &Gun from the Front, Pushing into Stomach -  Live Side/Dead Side", level = 3),
                Technique(name = "Ground Fighting 1: Defense vs. Stomps", level = 3),
                Technique(name = "Choke Defenses 3: From Side, Extended/Condensed (Static) & From Side, Extended/Condensed (With a Push)", level = 3),
                Technique(name = "Handstrikes 3: Elbows 1-7", level = 3),
                Technique(name = "Choke Defenses 1:  Two Hand Pluck, Extended/Condensed (Front) and One Hand Pluck (Front)", level = 3),
                Technique(name = "Bear Hugs 1: Front with Arms Free (With Space/No Space) & Front with Arms Caught (With Space/No Space)", level = 3),
                Technique(name = "Bear Hugs 2:  Behind with Arms Free & Behind with Arms Caught", level = 3),
                Technique(name = "Baseball Bat Stabbing Defense", level = 3),
            )
            techniqueRepository.saveAll(techniques)

            // Create LessonQueues Table
            val lessonQueues = listOf(
                LessonQueue(name = "Level 1", level = 1),
                LessonQueue(name = "Level 2", level = 2),
                LessonQueue(name = "Level 3", level = 3)
            )
            lessonQueueRepository.saveAll(lessonQueues)

            // Create LessonQueueTechniques Table
            val lessonQueueTechniques: ArrayList<LessonQueueTechnique> = ArrayList()
            for (technique in techniques) {
                lessonQueueTechniques.add(LessonQueueTechnique(lessonQueue = lessonQueues[technique.level-1], technique = technique));
            }
            lessonQueueTechniqueRepository.saveAll(lessonQueueTechniques)

            // Create Events Table
            val events = listOf(
                Event(name="Level 1", startTime = LocalDateTime.now(), endTime = LocalDateTime.now(), isRecurring = false, level = 1),
                Event(name="Level 2", startTime = LocalDateTime.now(), endTime = LocalDateTime.now(), isRecurring = false, level = 2),
                Event(name="Level 3", startTime = LocalDateTime.now(), endTime = LocalDateTime.now(), isRecurring = false, level = 3)
            )
            classRepository.saveAll(events)

            // Create EventQueues Table
            val eventQueues = listOf(
                EventQueue(event = events[0], lessonQueue = lessonQueues[0]),
                EventQueue(event = events[1], lessonQueue = lessonQueues[1]),
                EventQueue(event = events[2], lessonQueue = lessonQueues[2])
            )
            eventQueueRepository.saveAll(eventQueues)
        }
    }
}